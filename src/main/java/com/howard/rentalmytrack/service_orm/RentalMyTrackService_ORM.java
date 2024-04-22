package com.howard.rentalmytrack.service_orm;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;

import java.util.List;
import java.util.Map;

public interface RentalMyTrackService_ORM {

    RentalMyTrackVo_ORM addEmp(RentalMyTrackVo_ORM emp);

    RentalMyTrackVo_ORM updateEmp(RentalMyTrackVo_ORM emp);

    void deleteEmp(Integer empno);

    RentalMyTrackVo_ORM getEmpByEmpno(Integer empno);

    List<RentalMyTrackVo_ORM> getAllEmps(int currentPage);

    int getPageTotal();

    List<RentalMyTrackVo_ORM> getEmpsByCompositeQuery(Map<String, String[]> map);

}
