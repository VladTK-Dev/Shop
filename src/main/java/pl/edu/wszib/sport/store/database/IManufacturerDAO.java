package pl.edu.wszib.sport.store.database;

import pl.edu.wszib.sport.store.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface IManufacturerDAO {

    Optional<Manufacturer> getManufacturerById(int mId);

    List<Manufacturer> getManufacturerByType(String mType);

    void addManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Manufacturer manufacturer);
}
