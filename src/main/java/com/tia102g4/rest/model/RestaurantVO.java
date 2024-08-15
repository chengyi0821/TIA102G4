package com.tia102g4.rest.model;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RestaurantVO {
    private String restName;
    private Long restType;
    private Long restId;
    private String description;
    
    public RestaurantVO() {
    	
    }

    public RestaurantVO(Long restId, Long restType, String restName, String description) {
        this.restId = restId;
        this.restType = restType;
        this.restName = restName;
        this.description = description;
    }
    
    public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public Long getRestType() {
		return restType;
	}

	public void setRestType(Long restType) {
		this.restType = restType;
	}

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void saveToRedis() {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            String key = restId + ":" + restType + ":" + restName +":" + description;
            jedis.set(key, "");
        }
    }

    public static RestaurantVO getFromRedis(String key) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            String[] parts = key.split(":");
            return new RestaurantVO(Long.parseLong(parts[0]), Long.parseLong(parts[1]), parts[2],parts[3]);
        }
    }
    
    //儲存餐廳的資料 restId : restName
    public void restToRedis(Long restId, String restName) {
    	try {Jedis jedis = new Jedis("localhost", 6379);
    	String key = String.valueOf(restId) ;
    	String value = restName;
    	jedis.set(key, value);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		}
    }
    
    public static List<RestaurantVO> getRestaurantsByIds(List<Long> ids){
		List<RestaurantVO> restaurants = new ArrayList<>();
		for(Long id : ids) {
			RestaurantVO restaurant = new RestaurantVO();
			restaurant.setRestId(id);
			restaurants.add(restaurant);
		}
		return restaurants;
	}
}
