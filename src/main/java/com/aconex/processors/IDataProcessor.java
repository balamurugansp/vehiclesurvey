package com.aconex.processors;

import com.aconex.model.VehicleEntry;

import java.util.List;

public interface IDataProcessor {
    String process(List<VehicleEntry> entries);
}
