package com.svetlanakuro.mvp_mvvm_patterns.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.svetlanakuro.mvp_mvvm_patterns.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.signInButton.setOnClickListener {
            presenter.onSignIn(
                binding.loginEditText.text.toString(), binding.passwordEditText.text.toString()
            )
        }

        binding.signUpButton.setOnClickListener {
            presenter.onSignUp(
                binding.loginEditText.text.toString(), binding.passwordEditText.text.toString()
            )
        }

        binding.forgotPasswordTextView.setOnClickListener {
            presenter.onForgotPassword(binding.loginEditText.text.toString())
        }
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return super.getLastNonConfigurationInstance()
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun setSuccess(login: String) {
        Toast.makeText(this, "Welcome, $login!", Toast.LENGTH_SHORT).show()
    }

    override fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.loginEditText.visibility = View.GONE
        binding.passwordEditText.visibility = View.GONE
        binding.signInButton.visibility = View.GONE
        binding.signUpButton.visibility = View.GONE
        binding.forgotPasswordTextView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.loginEditText.visibility = View.VISIBLE
        binding.passwordEditText.visibility = View.VISIBLE
        binding.signInButton.visibility = View.VISIBLE
        binding.signUpButton.visibility = View.VISIBLE
        binding.forgotPasswordTextView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
    }

    override fun addAccountSuccess(login: String) {
        Toast.makeText(this, "Account for $login is created", Toast.LENGTH_SHORT).show()
    }

    override fun resetPasswordSuccess(password: String) {
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