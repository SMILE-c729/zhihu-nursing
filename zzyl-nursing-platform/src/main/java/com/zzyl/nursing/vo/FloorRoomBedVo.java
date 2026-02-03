package com.zzyl.nursing.vo;

import lombok.Data;

@Data
public class FloorRoomBedVo {
    private Long floorId;
    private String floorName;
    private Long roomId;
    private String roomCode;
    private Long bedId;
    private String bedNumber;
}
