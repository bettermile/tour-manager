package com.bettermile.tourmanager.repositories

import com.bettermile.tourmanager.model.Tour
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TourRepository : CrudRepository<Tour, Long>
