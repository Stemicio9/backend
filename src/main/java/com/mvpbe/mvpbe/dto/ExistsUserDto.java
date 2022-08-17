package com.mvpbe.mvpbe.dto;

public class ExistsUserDto {

        private boolean exists;

        public ExistsUserDto(){}

        public ExistsUserDto(boolean exists){
            this.exists = exists;
        }

        public boolean isExists() {
            return exists;
        }

        public void setExists(boolean exists) {
            this.exists = exists;
        }



}
