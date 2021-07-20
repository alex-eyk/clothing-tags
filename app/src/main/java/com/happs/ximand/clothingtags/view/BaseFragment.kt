package com.happs.ximand.clothingtags.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.happs.ximand.clothingtags.BR
import com.happs.ximand.clothingtags.viewmodel.BaseViewModel
import com.happs.ximand.clothingtags.viewmodel.Event
import java.lang.reflect.ParameterizedType

/**
 * Абстрактный класс, описывающий базовый фрагмент
 * Каждый фрагмент приложения имеет свою модель представления и сгенерированный DataBinding объект
 * Для возможности повторного использования кода создание модели представления и DataBinding объекта
 * вынесено в этот базовый класс.
 *
 * Обязательное условие: XML-файл, описывающий фрагмент, обязательно должен содержать переменную
 * того же типа, что и дженерик VM, а так же называющуюся 'viewModel'.
 *
 * Стоит помнить: модель представления создается в методе onCreate, объект DataBinding в методе
 * onCreateView. При попытке получить эти объекты раньше будет брошено IllegalStateException.
 *
 * @param <VM> Модель представления фрагмента
 * @param <B>  Data binding класс фрагмента
 *
 * @param layoutRes id XML файла, описывающего фрагметн (т.е. R.layout.fragment_...)
 * @param menuRes id XML файла, описывающего меню Action bar`а (т.е. R.menu.menu_...)
 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding>(
    private val layoutRes: Int, private val menuRes: Int
) : Fragment() {

    protected lateinit var viewModel: VM

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(menuRes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onOptionsMenuItemClicked(id)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setHasOptionsMenu(true)
        viewModel = ViewModelProvider(
            this, SavedStateViewModelFactory(activity?.application!!, this)
        ).get(getGenericForPosition(0))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onViewDataBindingCreated(binding)
        observeMakeSnackbarEvent()

        onPreViewModelAttached(viewModel)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        return binding.root
    }

    private fun observeMakeSnackbarEvent() {
        viewModel.makeSnackbarEvent.observe(viewLifecycleOwner, Observer { event ->
            makeSnackbarOnEvent(event)
        })
    }

    protected fun makeSnackbarOnEvent(event: Event<Int>) {
        event.getContentIfNotHandled()?.let {
            Snackbar.make(view!!, it, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * Вызвается после создания объекта DataBinding. Этот метод следует переопределить в
     * наследующихся классах, если это необходимо.
     * При этом модель представления уже создана и может быть получена вызовом метода getViewModel().
     */
    protected open fun onViewDataBindingCreated(binding: B) {

    }

    /**
     * Вызывается после создания модели представления и объекта DataBinding, но перед тем, как
     * модель будет прикреплена к объекту DataBinding. Этот метод следует переопределить в
     * наследующихся классах, если это необходимо.
     */
    protected open fun onPreViewModelAttached(viewModel: VM) {

    }

    /**
     * Вызывается, если произошло событие в другом фрагменте, которое требует изменить состояние
     * текущего фрагмента.
     */
    open fun onExternalEvent(eventId: Int) {

    }

    protected fun setActionBarTitle(resId: Int) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle(resId)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> getGenericForPosition(position: Int): Class<T> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[position] as Class<T>
    }
}