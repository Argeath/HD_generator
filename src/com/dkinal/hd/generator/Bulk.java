package com.dkinal.hd.generator;

import java.util.List;

public class Bulk {
    static String build(List<String> params) {
        StringBuilder sb = new StringBuilder();

        for(String e : params) {
            sb.append(e);
            sb.append('|');
        }

        sb.deleteCharAt(sb.length() - 1); // remove last |

        return sb.toString();
    }
}
