package com.bin.pojo;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.pojo
 * @ClassName: Status
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 15:14
 */
public enum Status {
    PROCESSING("PROCESSING", "未发货"),
    SHIPPED("SHIPPED", "已发货"),
    DELIVERED("DELIVERED", "已签收");

    private final String statusName;
    private final String statusDesc;

    private Status(String statusName, String statusDesc) {
        this.statusName = statusName;
        this.statusDesc = statusDesc;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    @Override
    public String toString() {
        return statusDesc;
    }

    public static void main(String[] args) {
        String name = "SHIPPED";
        Status status = DELIVERED;
        status = Status.valueOf(name);
        System.out.println(status);
        System.out.println(status.getStatusName() == "SHIPPED");
//        Status status = Status.valueOf("SHIPPED");
//        System.out.println(status.toString());
    }
}
