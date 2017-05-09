package com.sanskar.campus.campus;

/**
 * Created by Jai on 2017-04-10.
 */

public class FacultyContactCS {
    String name,id;

   @Override
    public String toString() {
        return name+"|"+id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
