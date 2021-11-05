/*
 * 
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 5. 2. 오후 2:28:55
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import open.commons.json.model.JSONArrayFactory;

/**
 * 
 * @since 2014. 5. 2.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class TEST_JSONArray {

    public static void main(String[] args) throws JSONException {
        String jsonStr = "[1,2,3,4,5,\"6\",7,8,9,0]";

        int[] intArr = (int[]) JSONArrayFactory.createArray(jsonStr, int.class);

        System.out.println(Arrays.toString(intArr));

        @SuppressWarnings("unchecked")
        Collection<Integer> intCol = JSONArrayFactory.createCollection(jsonStr, List.class, int.class);

        System.out.println(intCol);

        intArr = JSONArrayFactory.intArray(new JSONArray(jsonStr));

        System.out.println(Arrays.toString(intArr));

        String[] strArr = (String[]) JSONArrayFactory.createArray(jsonStr, String.class);

        System.out.println(Arrays.toString(strArr));

    }

}
