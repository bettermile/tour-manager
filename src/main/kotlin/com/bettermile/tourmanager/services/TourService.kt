package com.bettermile.tourmanager.services

import com.bettermile.tourmanager.model.Driver
import com.bettermile.tourmanager.model.Tour
import com.bettermile.tourmanager.repositories.DriverRepository
import com.bettermile.tourmanager.repositories.TourRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class TourService(
    private val tourRepository: TourRepository,
    private val driverRepository: DriverRepository
) {
    private val tours: MutableList<Tour> = mutableListOf()
    private val drivers: MutableList<Driver> = mutableListOf()

    fun retrieveAllTours(): List<Tour> {
        if (tours.isEmpty()) {
            tours.addAll(tourRepository.findAll())
        }
        return tours
    }

    fun saveTour(tour: Tour) {
        try {
            tourRepository.save(tour)
            tours.add(tour)
        } catch (e: Exception) {
            println("Error saving tour: ${e.message}")
        }
    }

    fun saveDriver(driver: Driver) {
        try {
            drivers.add(driver)
            driverRepository.save(driver)
        } catch (ex: Exception) {
            println("Error saving driver")
        }
    }

    fun linkDriverToTour(tourId: Long, driverId: Long): String {
        val tour = tours.find { it.id == tourId }
        val driver = drivers.find { it.id == driverId }
        if (tour != null && driver != null) {
            tour.driver = driver
            tourRepository.save(tour)
            return "Driver linked successfully."
        } else {
            return "Driver or Tour not found!"
        }
    }

    fun deleteTour(id: Long) {
        tours.removeIf { it.id == id }
    }
}
