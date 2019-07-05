package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sc-provider",
        fallback = NewsServiceError.class)

public interface FeignService  extends CloudService{

}
