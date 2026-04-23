package com.zzyl.ai.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 剩余病床响应对象。
 */
@Data
public class AiBedAvailabilityVo
{
    /**
     * 汇总信息。
     */
    private Summary summary = new Summary();

    /**
     * 按楼层拆分后的剩余病床信息。
     */
    private List<FloorItem> floors = new ArrayList<>();

    /**
     * 剩余病床汇总。
     */
    @Data
    public static class Summary
    {
        /**
         * 剩余病床数。
         */
        private Integer availableBedCount = 0;

        /**
         * 有空床的房间数。
         */
        private Integer availableRoomCount = 0;

        /**
         * 有空床的楼层数。
         */
        private Integer availableFloorCount = 0;
    }

    /**
     * 楼层信息。
     */
    @Data
    public static class FloorItem
    {
        /**
         * 楼层 ID。
         */
        private Long floorId;

        /**
         * 楼层名称。
         */
        private String floorName;

        /**
         * 当前楼层剩余病床数。
         */
        private Integer availableBedCount = 0;

        /**
         * 当前楼层下有空床的房间列表。
         */
        private List<RoomItem> rooms = new ArrayList<>();
    }

    /**
     * 房间信息。
     */
    @Data
    public static class RoomItem
    {
        /**
         * 房间 ID。
         */
        private Long roomId;

        /**
         * 房间编号。
         */
        private String roomCode;

        /**
         * 当前房间剩余病床数。
         */
        private Integer availableBedCount = 0;

        /**
         * 当前房间空床列表。
         */
        private List<BedItem> beds = new ArrayList<>();
    }

    /**
     * 病床信息。
     */
    @Data
    public static class BedItem
    {
        /**
         * 病床 ID。
         */
        private Long wardBedId;

        /**
         * 病床编号。
         */
        private String wardBedNo;
    }
}
