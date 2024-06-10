package com.example.groupprojectoop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPassword {


    public boolean isValidPassword(String password) {
      if (password.length ()<8){
          return false;

      }
      else return true;

    }
}
