package com.example.demo.Service;

import com.example.demo.Module.Area;
import java.util.List;

public interface AreaService {
    Area addArea(Area area);
    List<Area> getAllAreas();
    Area getAreaById(Long id);
    boolean deleteArea(Long id);
    Area updateArea(Long id, Area area);
}
