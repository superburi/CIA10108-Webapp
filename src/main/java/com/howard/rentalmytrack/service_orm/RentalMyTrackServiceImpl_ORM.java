package com.howard.rentalmytrack.service_orm;

import com.howard.rentalmytrack.dao_orm.RentalMyTrackDaoImpl_ORM;
import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import static com.howard.util.Constants.PAGE_MAX_RESULT;

public class RentalMyTrackServiceImpl_ORM implements RentalMyTrackService_ORM{

    private RentalMyTrackDaoImpl_ORM dao;

    public RentalMyTrackServiceImpl_ORM() {
        dao = new RentalMyTrackDaoImpl_ORM();
    }

    @Override
    public RentalMyTrackVo_ORM addTrack(RentalMyTrackVo_ORM entity) {

        Map<String, Integer> map = new HashMap<>();
        map = dao.insert(entity);
        return dao.getById(map.get("rNo"), map.get("memNo"));

    }

    @Override
    public RentalMyTrackVo_ORM updateTrack(RentalMyTrackVo_ORM entity) {

        Map<String, Integer> map = new HashMap<>();
        map = dao.update(entity);
        if (!map.isEmpty()) {
            return dao.getById(map.get("rNo"), map.get("memNo"));
        } else {
            return null; // 如果回傳的 map 是空的，就回傳 null
        }

    }

    @Override
    public void deleteTrack(Integer rNo, Integer memNo) {
        dao.delete(rNo, memNo);
    }

    @Override
    public RentalMyTrackVo_ORM getById(Integer rNo, Integer memNo) {
        return dao.getById(rNo, memNo);
    }

    @Override
    public List<RentalMyTrackVo_ORM> getAllTracks(int currentPage) {
        return dao.getAll(currentPage);
    }

    @Override
    public int getPageTotal() {

        long total = dao.getTotal();
        // 計算Emp數量每頁3筆的話總共有幾頁
        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
        return pageQty;

    }

    @Override
    public List<RentalMyTrackVo_ORM> getTracksByCompositeQuery(Map<String, String[]> map) {

        Map<String, String> query = new HashMap<>();
        // Map.Entry即代表一組key-value
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();
            // 因為請求參數裡包含了action，做個去除動作
            if ("action".equals(key)) {
                continue;
            }
            // 若是value為空即代表沒有查詢條件，做個去除動作
            String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
            if (value == null || value.isEmpty()) {
                continue;
            }
            query.put(key, value);
        }

        System.out.println(query);

        return dao.getByCompositeQuery(query);

    }

}
