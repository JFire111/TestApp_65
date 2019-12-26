package com.vinapp.testapp65.ui.fragments.interfaces;

import com.vinapp.testapp65.logic.data.Worker;

/**
 * Интерфейс для взаимодействия с WorkerListFragment и его адаптером
 */

public interface OnWorkersListFragmentListener {

    void openWorkerDataFragment(Worker worker);
}
