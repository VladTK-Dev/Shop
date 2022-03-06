package pl.edu.wszib.sport.store.service;

import pl.edu.wszib.sport.store.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface IManufacturerService {

    Optional<Manufacturer> getManufacturerById(int bookId);

    List<Manufacturer> getManufacturerByType(String type);

    void addManufacturer(Manufacturer manufacturer);
}
