package com.proto.service.client.erpservice.bom;


import java.util.List;

public class BomServiceForApp {
    private BOM bom;

    public BomServiceForApp() {
        BOMService bomService=new BOMService();
        this.bom=bomService.getBOMPort();
    }

    public List<BomItem> getAllBom(){
        return bom.getAllBOM();
    }
}
