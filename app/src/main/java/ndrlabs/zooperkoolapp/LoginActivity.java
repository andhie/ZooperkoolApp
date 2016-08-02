package ndrlabs.zooperkoolapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private ImageButton pwVisible;
    private Button login;
    private TextView forgotPw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            // dont do this in real app
            // this is to demonstrate the effect background color 'changed' on slower phones
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();


        Drawable drawable = tintIcon(pwVisible.getContext(), pwVisible.getDrawable());
        pwVisible.setImageDrawable(drawable);

    }

    private void findViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        pwVisible = (ImageButton) findViewById(R.id.pw_visible);
        login = (Button) findViewById(R.id.login);
        forgotPw = (TextView) findViewById(R.id.forgot_pw);

        pwVisible.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        //SHOW PASSWORD HERE
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return false; // false to let default touch behavior take effect

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        //HIDE PASSWORD HERE
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return false; // false to let default touch behavior take effect

                    default:
                        return false;
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_SHORT).show();
            }
        });
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Drawable tintIcon(Context context, Drawable drawable) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorControlNormal, outValue, true);

        Drawable mutated = drawable.mutate();
        int color = ContextCompat.getColor(context, outValue.resourceId);
        mutated.setColorFilter(color, PorterDuff.Mode.SRC_IN);

        return mutated;
    }

}
