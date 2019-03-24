package com.kanaa.cwapi.web;

import com.kanaa.cwapi.common.UserException;
import com.kanaa.cwapi.common.Weather;

public interface Processor {
  Weather process(String answer) throws UserException;
}
