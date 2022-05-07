package gp.cnusambe.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private Object oss_license;
    private Object license_restriction_map;

    public Message(){
        this.oss_license = null;
        this.license_restriction_map = null;
    }
}
