package lk.ijse.Crop_monitoring_system.Repository;

import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepo extends JpaRepository<FieldEntity,String> {

}