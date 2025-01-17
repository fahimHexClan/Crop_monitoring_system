package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.ResponseDto.StandardResponse;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/Vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/saveVehicle")
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody VehicleDto vehicleDto) {

        try {
            String id = vehicleService.addVehicle(vehicleDto);
            if (id.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "Vehicle Succesfully saved  : ", id), HttpStatus.CREATED);//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (id.equals("06")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "Vehicle Not saved  : ", id), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(path = "/updateVehicle")
    public ResponseEntity<StandardResponse> updateVehicle(@RequestBody VehicleDto vehicleDto) {

        try {
            String id = vehicleService.updateVehicle(vehicleDto);
            if (id.equals("00")) {

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "Vehicle Succesfully update  : ", id), HttpStatus.CREATED);

            } else if (id.equals("01")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "Vehicle Not update  : ", id), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/get_All_Vehicle"})
    public ResponseEntity<StandardResponse> getAllVehicle() {
        try {
            List<VehicleDto> allVehicles = vehicleService.getAllVehicles();
            return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully get  ", allVehicles), HttpStatus.ACCEPTED//data ekak aluthen get karama ok kiyala return karanawa
            );

        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa

        }

    }

    @GetMapping(path = "/Search_Vehicle_By_Id/{vehicleId}")
    public ResponseEntity<StandardResponse> findVehicleByVehicleId(@PathVariable("vehicleId") Long vehicleId) {
        try {
            VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
            if (vehicleDto != null) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully get by Id  ", vehicleDto), HttpStatus.ACCEPTED);

            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, vehicleId + "  No Vehicle is avilable on this id  : ", vehicleId), HttpStatus.BAD_REQUEST);


            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/deleteVehicle/{vehicleID}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable("vehicleID") Long vehicleID) {
        try {
            String res = vehicleService.deleteVehicle(vehicleID);
            if (res.equals("00")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully delete by Id  ", vehicleID), HttpStatus.ACCEPTED);

            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, vehicleID + "  No Vehicle is avilable on this id  : ", vehicleID), HttpStatus.BAD_REQUEST);

            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllVehicleIds() {
        try {
            List<Long> vehicleIds = vehicleService.getAllVehicleIds();
            return new ResponseEntity<>(new StandardResponse(200, "Successfully Retrieved Vehicle IDs", vehicleIds), HttpStatus.OK);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(new StandardResponse(400, "Failed to Retrieve Vehicle IDs", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}






