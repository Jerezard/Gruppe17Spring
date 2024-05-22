package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Car;
import com.example.demo.Repository.CarRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController{

    @Autowired
    private CarRepository carRepository;
    private final Path imagePath = Paths.get("src/main/resources/static/images");

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName){
        try{
            Path file = imagePath.resolve(imageName);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e){
        return ResponseEntity.internalServerError().build();
    }
}

@GetMapping("/car/{carId}")
public ResponseEntity<Resource> getImageByCarId(@PathVariable int carId){
    Optional<Car> carOptional = carRepository.findById(carId);
    if(carOptional.isPresent()){
        Car car = carOptional.get();
        Path imagePath = Paths.get(car.getImagePath());
        try{
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    } else{
        return ResponseEntity.notFound().build();
    }
}
}