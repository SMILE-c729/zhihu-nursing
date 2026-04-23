package com.zzyl.ai.service.impl;

import com.zzyl.ai.config.AiToolProperties;
import com.zzyl.ai.constant.AiConstants;
import com.zzyl.ai.service.IAiToolService;
import com.zzyl.ai.vo.AiBedAvailabilityVo;
import com.zzyl.ai.vo.AiCurrentPlansVo;
import com.zzyl.common.exception.ServiceException;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.hospital.domain.WardFloor;
import com.zzyl.hospital.domain.CarePlan;
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.zzyl.hospital.service.IWardFloorService;
import com.zzyl.hospital.service.ICarePlanService;
import com.zzyl.hospital.service.IMedicalOrderItemService;
import com.zzyl.hospital.service.IWardRoomService;
import com.zzyl.hospital.vo.WardBedVo;
import com.zzyl.hospital.vo.CarePlanVo;
import com.zzyl.hospital.vo.CarePlanOrderItemVo;
import com.zzyl.hospital.vo.WardRoomVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * AI 工具服务实现。
 */
@Service
public class AiToolServiceImpl implements IAiToolService
{
    private final AiToolProperties aiToolProperties;

    private final ICarePlanService carePlanService;

    private final IMedicalOrderItemService medicalOrderItemService;

    private final IWardFloorService wardFloorService;

    private final IWardRoomService wardRoomService;

    public AiToolServiceImpl(AiToolProperties aiToolProperties, ICarePlanService carePlanService,
                             IMedicalOrderItemService medicalOrderItemService, IWardFloorService wardFloorService,
                             IWardRoomService wardRoomService)
    {
        this.aiToolProperties = aiToolProperties;
        this.carePlanService = carePlanService;
        this.medicalOrderItemService = medicalOrderItemService;
        this.wardFloorService = wardFloorService;
        this.wardRoomService = wardRoomService;
    }
    /**
     * 提取授权令牌。
     *
     * @param authorizationHeader 授权头
     * @return 授权令牌
     */
    @Override
    public void validateToolToken(String authorizationHeader)
    {
        String token = extractToken(authorizationHeader);
        if (StringUtils.isEmpty(token))
        {
            throw new ServiceException("缺少 Authorization Token");
        }
        if (!StringUtils.equals(token, aiToolProperties.getApiKey()))
        {
            throw new ServiceException("Authorization Token 无效");
        }
    }

    @Override
    public AiCurrentPlansVo getCurrentPlans()
    {
        List<CarePlan> plans = carePlanService.getAllCarePlans();
        AiCurrentPlansVo response = new AiCurrentPlansVo();
        response.setTotalPlanCount(plans.size());

        for (CarePlan plan : plans)
        {
            CarePlanVo detail = carePlanService.selectCarePlanById(plan.getId());
            AiCurrentPlansVo.PlanItem planItem = new AiCurrentPlansVo.PlanItem();
            planItem.setPlanId(detail.getId());
            planItem.setPlanName(detail.getPlanName());

            if (detail.getProjectPlans() != null)
            {
                for (CarePlanOrderItemVo projectPlan : detail.getProjectPlans())
                {
                    AiCurrentPlansVo.ProjectItem projectItem = new AiCurrentPlansVo.ProjectItem();
                    projectItem.setExecuteTime(projectPlan.getExecuteTime());
                    projectItem.setExecuteCycleLabel(resolveExecuteCycleLabel(projectPlan.getExecuteCycle()));
                    projectItem.setExecuteFrequency(projectPlan.getExecuteFrequency());

                    if (StringUtils.isNotEmpty(projectPlan.getMedicalOrderItemId()))
                    {
                        Long projectId = Long.valueOf(projectPlan.getMedicalOrderItemId());
                        projectItem.setProjectId(projectId);

                        MedicalOrderItem project = medicalOrderItemService.selectMedicalOrderItemById(projectId);
                        if (project != null)
                        {
                            projectItem.setProjectName(project.getName());
                        }
                    }

                    planItem.getProjects().add(projectItem);
                }
            }

            response.getPlans().add(planItem);
        }

        return response;
    }

    @Override
    public AiBedAvailabilityVo getRemainingBeds()
    {
        AiBedAvailabilityVo response = new AiBedAvailabilityVo();
        List<WardFloor> floors = wardFloorService.list();

        for (WardFloor floor : floors)
        {
            List<WardRoomVo> rooms = wardRoomService.getWardRoomsByWardFloorId(floor.getId());
            AiBedAvailabilityVo.FloorItem floorItem = new AiBedAvailabilityVo.FloorItem();
            floorItem.setFloorId(floor.getId());
            floorItem.setFloorName(floor.getName());

            for (WardRoomVo room : rooms)
            {
                List<WardBedVo> bedVos = room.getWardBedVoList();
                if (bedVos == null)
                {
                    bedVos = new ArrayList<>();
                }

                AiBedAvailabilityVo.RoomItem roomItem = new AiBedAvailabilityVo.RoomItem();
                roomItem.setRoomId(room.getId());
                roomItem.setRoomCode(room.getCode());

                for (WardBedVo bedVo : bedVos)
                {
                    if (bedVo != null && Integer.valueOf(0).equals(bedVo.getWardBedStatus()))
                    {
                        AiBedAvailabilityVo.BedItem bedItem = new AiBedAvailabilityVo.BedItem();
                        bedItem.setWardBedId(bedVo.getId());
                        bedItem.setWardBedNo(bedVo.getWardBedNo());
                        roomItem.getBeds().add(bedItem);
                    }
                }

                if (!roomItem.getBeds().isEmpty())
                {
                    roomItem.setAvailableBedCount(roomItem.getBeds().size());
                    floorItem.getRooms().add(roomItem);
                    floorItem.setAvailableBedCount(floorItem.getAvailableBedCount() + roomItem.getAvailableBedCount());
                }
            }

            if (!floorItem.getRooms().isEmpty())
            {
                response.getFloors().add(floorItem);
            }
        }

        response.getSummary().setAvailableFloorCount(response.getFloors().size());
        int roomCount = 0;
        int bedCount = 0;
        for (AiBedAvailabilityVo.FloorItem floorItem : response.getFloors())
        {
            roomCount += floorItem.getRooms().size();
            bedCount += floorItem.getAvailableBedCount();
        }
        response.getSummary().setAvailableRoomCount(roomCount);
        response.getSummary().setAvailableBedCount(bedCount);
        return response;
    }

    private String resolveExecuteCycleLabel(String executeCycle)
    {
        if (StringUtils.equals("0", executeCycle))
        {
            return "天";
        }
        if (StringUtils.equals("1", executeCycle))
        {
            return "周";
        }
        if (StringUtils.equals("2", executeCycle))
        {
            return "月";
        }
        return "未知";
    }

    private String extractToken(String authorizationHeader)
    {
        if (StringUtils.isBlank(authorizationHeader))
        {
            return null;
        }
        if (StringUtils.startsWithIgnoreCase(authorizationHeader, AiConstants.AUTHORIZATION_PREFIX))
        {
            return authorizationHeader.substring(AiConstants.AUTHORIZATION_PREFIX.length()).trim();
        }
        return authorizationHeader.trim();
    }
}
