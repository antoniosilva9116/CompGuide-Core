/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author António
 */
public class GuidelineAdapterFactory {

    private static GuidelineAdapterFactory instance;

    private static final Map<String, GuidelineInterface> services
            = new HashMap<>();

    static {
//        services.put("dropbox",
//                new Dropbox()
//        );
//        services.put("googledrive",
//                new GoogleDrive()
//        );
    }

    public GuidelineAdapterFactory() {
    }

    public static GuidelineAdapterFactory instance() {
        if (instance == null) {
            instance = new GuidelineAdapterFactory();
        }
        return instance;
    }

    public GuidelineInterface getAdapter(String type) {
        return services.get(type);
    }

}
