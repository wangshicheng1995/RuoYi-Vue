package com.ruoyi.web.Entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IndividualFree {
    @NotNull
    String id;

    /*
     * EDC
     */
    @NotNull
    String EDCName;

    /*
     * 项目
     */
    @NotNull
    String projectName;

    /*
     * 单价
     */
    @NotNull
    Double price;

    /*
     * 税费
     */
    @NotNull
    String taxes;

    /*
     * 管理费
     */
    @NotNull
    String ManagementFree;

    public IndividualFree(String id,String EDCName, String projectName, Double price, String taxes, String ManagementFree) {
        this.id = id;
        this.EDCName = EDCName;
        this.projectName = projectName;
        this.price = price;
        this.taxes = taxes;
        this.ManagementFree = ManagementFree;
    }

}
