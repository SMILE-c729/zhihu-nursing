package com.zzyl.hospital.vo;

import lombok.Data;

@Data
public class WardStructureVo {
    private Long wardFloorId;
    private String wardFloorName;
    private Long wardRoomId;
    private String wardRoomCode;
    private Long wardBedId;
    private String wardBedNo;
}
