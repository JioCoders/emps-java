package com.springboot.empc.model.res;

import java.util.ArrayList;
import java.util.List;

import com.springboot.empc.base.ResponseBase;
import com.springboot.empc.model.res.data.EmpData;

public class ResponseEmpList extends ResponseBase {

    List<EmpData> empList = new ArrayList<>();

    public List<EmpData> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmpData> empList) {
        this.empList = empList;
    }
}
