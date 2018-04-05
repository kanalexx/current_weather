package com.kanaa.cwapi.common;

public interface Processor {
  Weather process(String answer) throws UserException;
}
