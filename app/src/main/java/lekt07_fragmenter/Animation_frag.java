package lekt07_fragmenter;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import dk.nordfalk.android.elementer.R;

/**
 * Created by j on 30-09-14.
 */
public class Animation_frag extends Fragment {
  private ViewGroup rod;
  private Button knap1, knap2, knap3;

  @Override
  public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
    rod = (ViewGroup) i.inflate(R.layout.lekt01_tre_knapper, container, false);
    knap1 = rod.findViewById(R.id.knap1);
    knap1.setText("Animation");
    knap2 = rod.findViewById(R.id.knap2);
    knap2.setText("Elastisk knap");
    knap3 = rod.findViewById(R.id.knap3);
    knap3.setText("Sprængfuld af energi");

    knap1.setOnTouchListener(new View.OnTouchListener() {
      float knap1x0;

      @Override
      public boolean onTouch(View v, MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
          knap1.animate().translationY(150).rotationX(135).alpha(0.5f);
          knap1x0 = me.getRawX() + knap1.getX();
        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
          // Flyt knappen vandret så den følger trykpunktet
          knap1.setX(me.getRawX() - knap1x0);
        } else if (me.getAction() == MotionEvent.ACTION_UP) {
          knap1.animate().translationY(0).translationX(0)
                  .setInterpolator(new OvershootInterpolator())
                  .rotationX(0).alpha(1)
                  .setDuration(750);
        }
        return false;
      }
    });

    // Kilde: LiveButton-eksempel på DevBytes kanalen på developer.android.com
    final DecelerateInterpolator sDecelerator = new DecelerateInterpolator();
    final OvershootInterpolator sOvershooter = new OvershootInterpolator(10f);
    knap2.animate().setDuration(200);
    knap2.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
          knap2.animate().setInterpolator(sDecelerator).scaleX(.7f).scaleY(.7f);
        } else if (me.getAction() == MotionEvent.ACTION_UP) {
          knap2.animate().setInterpolator(sOvershooter).scaleX(1f).scaleY(1f);
          rod.findViewById(R.id.ikon).setVisibility(View.VISIBLE);
        }
        return false;
      }
    });

    knap3.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
          knap3.animate().setInterpolator(new ParrabelInterpolator()).scaleX(1.5f).scaleY(1.5f).rotation(10);
          rod.animate().setInterpolator(sDecelerator).scaleX(.9f).scaleY(.9f).rotationX(10);
        } else if (me.getAction() == MotionEvent.ACTION_UP) {
          knap3.animate().scaleX(1).scaleY(1).rotation(0).setInterpolator(sOvershooter);
          rod.animate().setInterpolator(sOvershooter).scaleX(1f).scaleY(1f).rotationX(0);
          rod.findViewById(R.id.ikon).setVisibility(View.GONE);
        }
        return false;
      }
    });

    // animer layoutændringer, dvs når ikon forsvinder eller bliver synligt igen:
    // Kunne også havde sat det deklarativt med android:animateLayoutChanges="true"
    rod.setLayoutTransition(new LayoutTransition());
    return rod;
  }


  static class ParrabelInterpolator implements Interpolator {
    private final float mTension = 10;

    /**
     * Maps a value representing the elapsed fraction of an animation to a value that represents
     * the interpolated fraction. This interpolated value is then multiplied by the change in
     * value of an animation to derive the animated value at the current elapsed animation time.
     *
     * @param t A value between 0 and 1.0 indicating our current point
     *          in the animation where 0 represents the start and 1.0 represents
     *          the end
     * @return The interpolation value. This value can be more than 1.0 for
     * interpolators which overshoot their targets, or less than 0 for
     * interpolators that undershoot their targets.
     */
    public float getInterpolation(float t) {
      // bemærk, for t=1 skullle vi egentlig ende på 1,
      // så det her er ikke nogen helt velfungerende interpolator, da den ender på 0!
      return t * (1 - t) * mTension;
    }
  }
}
