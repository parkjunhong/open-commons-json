/*
 * 
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 6. 3. 오후 11:49:02
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import open.commons.core.io.IMarshaller;
import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

/**
 * 
 * @since 2014. 6. 3.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class NonJSONField {
    
    public static void main(String[] args) {
        
    }
    
    
    static class NonJSONFieldClass extends DefaultJSONModel {
        
        @JSONField
        Calendar date;
        
        public static final IMarshaller<Calendar> marshaller = new IMarshaller<Calendar>() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            @Override
            public Object marshall(Calendar value) {
                return format.format(value.getTime());
            }

            @Override
            public Calendar unmarshall(Object value) {
                return null;
            }
        };
    }
}
