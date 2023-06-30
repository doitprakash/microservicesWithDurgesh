package com.main.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.user.entity.Hotel;
import com.main.user.entity.Rating;
import com.main.user.entity.User;
import com.main.user.repository.UserRepository;
import com.main.user.service.UserService;
import com.main.user.service.exceptions.UserIdNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userrepository;
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		  String randomUserId = UUID.randomUUID().toString();
		  user.setUserId(randomUserId);
		 
		return userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepository.findAll();
	}

	@Override
	public User getUser(String userId) {
	  User user = userrepository.findById(userId).orElseThrow(()->new UserIdNotFoundException("User ID with given response is not found :"+userId));
	   
	  Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICES/rating/user/"+user.getUserId() , Rating[].class);
	   logger.info("{}", ratingOfUser);
	   List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
	   
	   List<Rating>ratingList = ratings.stream().map(rating ->{
		   
		  ResponseEntity<Hotel>forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(),Hotel.class);
		  Hotel  hotel = forEntity.getBody();
		   rating.setHotel(hotel);
		   return rating;
	   }).collect(Collectors.toList());
	   user.setRatings(ratingList );
	   return user;
	
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> saveUsers(List<User> users) {
		 String randomUserId = UUID.randomUUID().toString();
		 for(User user: users) {
			 user.setUserId(randomUserId);
		 }
		List<User> usersList = userrepository.saveAll(users);
		return usersList;
	}

}
