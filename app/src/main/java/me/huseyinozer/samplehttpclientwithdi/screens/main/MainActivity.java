package me.huseyinozer.samplehttpclientwithdi.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.huseyinozer.samplehttpclientwithdi.R;
import me.huseyinozer.samplehttpclientwithdi.application.App;
import me.huseyinozer.samplehttpclientwithdi.application.ApplicationComponent;
import me.huseyinozer.samplehttpclientwithdi.networking.HttpClient;

public class MainActivity extends AppCompatActivity {

    @Inject
    HttpClient httpClient;

    @BindView(R.id.message_label)
    TextView messageLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Inject parameters
        getApplicationComponent().inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        App app = (App) getApplication();
        return app.getApplicationComponent();
    }

    @OnClick(R.id.button_success_request)
    public void onClickSuccessRequest() {
        httpClient.get("5904e13e10000015194f671f", result -> {

            messageLabel.setText(String.format("Response : %s", result));

        }, (statusCode, errorMessage, error) -> {

            messageLabel.setText(String.format("StatusCode : %s) \nError Message : %s", statusCode, errorMessage));

        });
    }

    @OnClick(R.id.button_error_request)
    public void onClickErrorRequest() {
        httpClient.get("5904e985100000a0194f6728", result -> {

            messageLabel.setText(String.format("Response : %s", result));

        }, (statusCode, errorMessage, error) -> {

            messageLabel.setText(String.format("StatusCode : %s \nError Message : %s", statusCode, errorMessage));

        });
    }
}
