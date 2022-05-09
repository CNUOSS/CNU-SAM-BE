//FOR TEST
package gp.cnusambe.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Message {
    private Object oss_license;
    private ArrayList<String> restriction;

    public Message(){
        this.oss_license = null;
        this.restriction = null;
    }
}
