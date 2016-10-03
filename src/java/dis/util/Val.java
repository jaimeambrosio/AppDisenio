/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis.util;

/**
 *
 * @author Jaime Ambrosio
 */
public class Val {

    public static Boolean hayText(String t) {
        if (t!=null) {
            if (!t.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
