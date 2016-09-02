package com.gymapp.rick.gymapp_android.respository;

import java.util.Set;

/**
 * Created by Rick on 22-Apr-16.
 */

/**template*/
public interface Repository <E, ID> {
        E findById(ID id);

        E save(E entity);

        E update(E entity);

        E delete(E entity);

        Set<E> findAll();

        int deleteAll();
}
