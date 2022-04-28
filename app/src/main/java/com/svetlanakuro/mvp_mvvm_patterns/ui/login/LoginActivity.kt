package com.svetlanakuro.mvp_mvvm_patterns.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.svetlanakuro.mvp_mvvm_patterns.app
import com.svetlanakuro.mvp_mvvm_patterns.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()

        binding.signInButton.setOnClickListener {
            viewModel.onSignIn(
                binding.loginEditText.text.toString(), binding.passwordEditText.text.toString()
            )
        }

        binding.signUpButton.setOnClickListener {
            viewModel.onSignUp(
                binding.loginEditText.text.toString(), binding.passwordEditText.text.toString()
            )
        }

        binding.forgotPasswordTextView.setOnClickListener {
            viewModel.onForgotPassword(binding.loginEditText.text.toString())
        }

        viewModel.shouldShowProgress.subscribe { shouldShow ->
            if (shouldShow) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel.isSuccess.subscribe { login ->
            setSuccess(login)
        }

        viewModel.errorText.subscribe { error ->
            setError(error)
        }

        viewModel.addAccountSuccess.subscribe { login ->
            addAccountSuccess(login)
        }

        viewModel.resetPasswordSuccess.subscribe { password ->
            resetPasswordSuccess(password)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.shouldShowProgress.unsubscribeAll()
        viewModel.isSuccess.unsubscribeAll()
        viewModel.errorText.unsubscribeAll()
        viewModel.addAccountSuccess.unsubscribeAll()
        viewModel.resetPasswordSuccess.unsubscribeAll()
    }

    private fun restoreViewModel(): LoginViewModel {
        val presenter = lastNonConfigurationInstance as? LoginViewModel
        return presenter ?: LoginViewModel(app.loginUsecase)
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return super.getLastNonConfigurationInstance()
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any {
        return viewModel
    }

    private fun setSuccess(login: String) {
        Toast.makeText(this, "Welcome, $login!", Toast.LENGTH_SHORT).show()
    }

    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.loginEditText.visibility = View.GONE
        binding.passwordEditText.visibility = View.GONE
        binding.signInButton.visibility = View.GONE
        binding.signUpButton.visibility = View.GONE
        binding.forgotPasswordTextView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.loginEditText.visibility = View.VISIBLE
        binding.passwordEditText.visibility = View.VISIBLE
        binding.signInButton.visibility = View.VISIBLE
        binding.signUpButton.visibility = View.VISIBLE
        binding.forgotPasswordTextView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
    }

    private fun addAccountSuccess(login: String) {
        Toast.makeText(this, "Account for $login is created", Toast.LENGTH_SHORT).show()
    }

    private fun resetPasswordSuccess(password: String) {
        Toast.makeText(this, "Your password: $password", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}