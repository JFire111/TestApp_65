package com.vinapp.testapp65.ui.fragments.interfaces;

import com.vinapp.testapp65.logic.data.Specialty;

/**
 * Интерфейс для взаимодействия со SpecialtiesFragment и его адаптером
 */

public interface OnSpecialitiesFragmentListener {

    void openWorkersListFragment(Specialty specialty);
}
