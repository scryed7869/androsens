/* 
 * Copyright (C) 2010 Christian Schneider
 * 
 * This file is part of Androsens.
 * 
 * Androsens is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Androsens is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenSudoku.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package tritop.android.androsens;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.hardware.*;

public class Androsens extends Activity {
	
	static final int FLOATTOINTPRECISION = 100;
	
	TextView oriHead,accHead,magHead,tv_orientationA,tv_orientationB,tv_orientationC,tv_accelA,tv_accelB,tv_accelC,tv_magneticA,tv_magneticB,tv_magneticC;
	ProgressBar pb_orientationA,pb_orientationB,pb_orientationC,pb_accelA,pb_accelB,pb_accelC,pb_magneticA,pb_magneticB,pb_magneticC;
	SensorManager m_sensormgr;
	List<Sensor> m_sensorlist;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        oriHead = (TextView) this.findViewById(R.id.TextView_oriHead);
        accHead = (TextView) this.findViewById(R.id.TextView_accHead);
        magHead = (TextView) this.findViewById(R.id.TextView_magHead);
        tv_orientationA = (TextView) this.findViewById(R.id.TextView_oriA); pb_orientationA = (ProgressBar) this.findViewById(R.id.ProgressBar_oriA);
        tv_orientationB = (TextView) this.findViewById(R.id.TextView_oriB); pb_orientationB = (ProgressBar) this.findViewById(R.id.ProgressBar_oriB);
        tv_orientationC = (TextView) this.findViewById(R.id.TextView_oriC); pb_orientationC = (ProgressBar) this.findViewById(R.id.ProgressBar_oriC);
        tv_accelA = (TextView) this.findViewById(R.id.TextView_accA);       pb_accelA = (ProgressBar) this.findViewById(R.id.ProgressBar_accA);
        tv_accelB = (TextView) this.findViewById(R.id.TextView_accB);       pb_accelB = (ProgressBar) this.findViewById(R.id.ProgressBar_accB);
        tv_accelC = (TextView) this.findViewById(R.id.TextView_accC);       pb_accelC = (ProgressBar) this.findViewById(R.id.ProgressBar_accC);
        tv_magneticA = (TextView) this.findViewById(R.id.TextView_magA);    pb_magneticA = (ProgressBar) this.findViewById(R.id.ProgressBar_magA);
        tv_magneticB = (TextView) this.findViewById(R.id.TextView_magB);    pb_magneticB = (ProgressBar) this.findViewById(R.id.ProgressBar_magB);
        tv_magneticC = (TextView) this.findViewById(R.id.TextView_magC);    pb_magneticC = (ProgressBar) this.findViewById(R.id.ProgressBar_magC);
        
        m_sensormgr  = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        m_sensorlist =  m_sensormgr.getSensorList(Sensor.TYPE_ALL);
        if(!m_sensorlist.isEmpty()){
        	Sensor snsr;
        	for(int i=0;i<m_sensorlist.size();i++){
        		snsr=m_sensorlist.get(i);
        		String snstyp;
        		switch(snsr.getType()){
        		 case Sensor.TYPE_ACCELEROMETER     : snstyp="TYPE_ACCELEROMETER";break;
        		 case Sensor.TYPE_ALL               : snstyp="TYPE_ALL";break;
        		 case Sensor.TYPE_GYROSCOPE         : snstyp="TYPE_GYROSCOPE";break;
        		 case Sensor.TYPE_LIGHT             : snstyp="TYPE_LIGHT";break;
        	 	 case Sensor.TYPE_MAGNETIC_FIELD    : snstyp="TYPE_MAGNETIC_FIELD";break;
        		 case Sensor.TYPE_ORIENTATION       : snstyp="TYPE_ORIENTATION";break;
        		 case Sensor.TYPE_PRESSURE          : snstyp="TYPE_PRESSURE";break;
        		 case Sensor.TYPE_PROXIMITY         : snstyp="TYPE_PROXIMITY";break;
        		 case Sensor.TYPE_TEMPERATURE       : snstyp="TYPE_TEMPERATURE";break;
        		 default: snstyp="UNKNOWN_TYPE "+snsr.getType();break;
        		}
        		
        		if(snsr.getType()==Sensor.TYPE_ORIENTATION){
        			oriHead.append(snsr.getName()+"\nMaxRange: "+snsr.getMaximumRange()+"\nType: "+snstyp+"");
        			pb_orientationA.setMax((int)snsr.getMaximumRange());
        			pb_orientationB.setMax((int)snsr.getMaximumRange());
        			pb_orientationC.setMax((int)snsr.getMaximumRange());
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_ACCELEROMETER){
        			accHead.append(snsr.getName()+"\nMaxRange: "+snsr.getMaximumRange()+"\nType: "+snstyp+"");
        			pb_accelA.setMax((int)(snsr.getMaximumRange()*9.81*FLOATTOINTPRECISION));
        			pb_accelB.setMax((int)(snsr.getMaximumRange()*9.81*FLOATTOINTPRECISION));
        			pb_accelC.setMax((int)(snsr.getMaximumRange()*9.81*FLOATTOINTPRECISION));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_MAGNETIC_FIELD){
        			magHead.append(snsr.getName()+"\nMaxRange: "+snsr.getMaximumRange()+"\nType: "+snstyp+"");
        			pb_magneticA.setMax((int)snsr.getMaximumRange()*FLOATTOINTPRECISION);
        			pb_magneticB.setMax((int)snsr.getMaximumRange()*FLOATTOINTPRECISION);
        			pb_magneticC.setMax((int)snsr.getMaximumRange()*FLOATTOINTPRECISION);
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		
        	}
        }
    }
    SensorEventListener senseventListener = new SensorEventListener(){

		/* (non-Javadoc)
		 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
		 */
		@Override
		public void onSensorChanged(SensorEvent event) {
			if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
			       pb_orientationA.setProgress( (int)event.values[0]);
			       pb_orientationB.setProgress( Math.abs((int)event.values[1]));
			       pb_orientationC.setProgress( Math.abs((int)event.values[2]));
			       tv_orientationA.setText(""+event.values[0]);
			       tv_orientationB.setText(""+event.values[1]);
			       tv_orientationC.setText(""+event.values[2]);
			}
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				   pb_accelA.setProgress( Math.abs( (int) event.values[0]*FLOATTOINTPRECISION ));
				   pb_accelB.setProgress( Math.abs( (int) event.values[1]*FLOATTOINTPRECISION ));
				   pb_accelC.setProgress( Math.abs( (int) event.values[2]*FLOATTOINTPRECISION ));
				   tv_accelA.setText(String.format("%.3f",event.values[0]));
				   tv_accelB.setText(String.format("%.3f",event.values[1]));
				   tv_accelC.setText(String.format("%.3f",event.values[2]));
				}
			if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
				   pb_magneticA.setProgress( Math.abs((int)event.values[0]*FLOATTOINTPRECISION ));
				   pb_magneticB.setProgress( Math.abs((int)event.values[1]*FLOATTOINTPRECISION ));
				   pb_magneticC.setProgress( Math.abs((int)event.values[2]*FLOATTOINTPRECISION ));
				   tv_magneticA.setText(String.format("%.3f",event.values[0]));
				   tv_magneticB.setText(String.format("%.3f",event.values[1]));
				   tv_magneticC.setText(String.format("%.3f",event.values[2]));
				}
			
		}

		/* (non-Javadoc)
		 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
		 */
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
    	
    };
}