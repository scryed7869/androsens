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
 * along with Androsens.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package tritop.android.androsens;

import java.util.List;


import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.hardware.*;

public class Androsens extends TabActivity implements TabHost.OnTabChangeListener{
	
	static final int FLOATTOINTPRECISION = 100;
	
	TextView oriHead,accHead,magHead,ligHead,proxHead,presHead,tempHead,oriAccu,accAccu,magAccu,ligAccu,proxAccu,presAccu,tempAccu,tv_orientationA,tv_orientationB,tv_orientationC,tv_accelA,tv_accelB,tv_accelC,tv_magneticA,tv_magneticB,tv_magneticC,tv_lightA,tv_proxA,tv_presA,tv_tempA,tv_overview;
	ProgressBar pb_orientationA,pb_orientationB,pb_orientationC,pb_accelA,pb_accelB,pb_accelC,pb_magneticA,pb_magneticB,pb_magneticC,pb_lightA,pb_proxA,pb_presA,pb_tempA;
	SensorManager m_sensormgr;
	List<Sensor> m_sensorlist;
	protected TabHost mTabHost;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
        oriHead = (TextView) this.findViewById(R.id.TextView_oriHead);
        accHead = (TextView) this.findViewById(R.id.TextView_accHead);
        magHead = (TextView) this.findViewById(R.id.TextView_magHead);
        ligHead = (TextView) this.findViewById(R.id.TextView_ligHead);
        proxHead = (TextView) this.findViewById(R.id.TextView_proxHead);
        presHead = (TextView) this.findViewById(R.id.TextView_presHead);
        tempHead = (TextView) this.findViewById(R.id.TextView_tempHead);
        
        oriAccu = (TextView) this.findViewById(R.id.oriAccuracy);
        accAccu = (TextView) this.findViewById(R.id.accAccuracy);
        magAccu = (TextView) this.findViewById(R.id.magAccuracy);
        ligAccu = (TextView) this.findViewById(R.id.ligAccuracy);
        proxAccu = (TextView) this.findViewById(R.id.proxAccuracy);
        presAccu = (TextView) this.findViewById(R.id.presAccuracy);
        tempAccu = (TextView) this.findViewById(R.id.tempAccuracy);
        
        
        tv_orientationA = (TextView) this.findViewById(R.id.TextView_oriA); pb_orientationA = (ProgressBar) this.findViewById(R.id.ProgressBar_oriA);
        tv_orientationB = (TextView) this.findViewById(R.id.TextView_oriB); pb_orientationB = (ProgressBar) this.findViewById(R.id.ProgressBar_oriB);
        tv_orientationC = (TextView) this.findViewById(R.id.TextView_oriC); pb_orientationC = (ProgressBar) this.findViewById(R.id.ProgressBar_oriC);
        tv_accelA = (TextView) this.findViewById(R.id.TextView_accA);       pb_accelA = (ProgressBar) this.findViewById(R.id.ProgressBar_accA);
        tv_accelB = (TextView) this.findViewById(R.id.TextView_accB);       pb_accelB = (ProgressBar) this.findViewById(R.id.ProgressBar_accB);
        tv_accelC = (TextView) this.findViewById(R.id.TextView_accC);       pb_accelC = (ProgressBar) this.findViewById(R.id.ProgressBar_accC);
        tv_magneticA = (TextView) this.findViewById(R.id.TextView_magA);    pb_magneticA = (ProgressBar) this.findViewById(R.id.ProgressBar_magA);
        tv_magneticB = (TextView) this.findViewById(R.id.TextView_magB);    pb_magneticB = (ProgressBar) this.findViewById(R.id.ProgressBar_magB);
        tv_magneticC = (TextView) this.findViewById(R.id.TextView_magC);    pb_magneticC = (ProgressBar) this.findViewById(R.id.ProgressBar_magC);
        tv_lightA = (TextView) this.findViewById(R.id.TextView_ligA);    pb_lightA = (ProgressBar) this.findViewById(R.id.ProgressBar_ligA);
        tv_proxA = (TextView) this.findViewById(R.id.TextView_proxA);    pb_proxA = (ProgressBar) this.findViewById(R.id.ProgressBar_proxA);
        tv_presA = (TextView) this.findViewById(R.id.TextView_presA);    pb_presA = (ProgressBar) this.findViewById(R.id.ProgressBar_presA);
        tv_tempA = (TextView) this.findViewById(R.id.TextView_tempA);    pb_tempA = (ProgressBar) this.findViewById(R.id.ProgressBar_tempA);
        
        
        tv_overview= (TextView) this.findViewById(R.id.TextViewOverview);
        
        m_sensormgr  = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        m_sensorlist =  m_sensormgr.getSensorList(Sensor.TYPE_ALL);
        
        mTabHost = getTabHost();
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_1").setIndicator("Orientation").setContent(R.id.LinearLayout02));
        mTabHost.addTab(mTabHost.newTabSpec("tab_2").setIndicator("Acceleration").setContent(R.id.LinearLayout03));
        mTabHost.addTab(mTabHost.newTabSpec("tab_3").setIndicator("Magnetic field").setContent(R.id.LinearLayout04));
        mTabHost.addTab(mTabHost.newTabSpec("tab_4").setIndicator("Light").setContent(R.id.LinearLayoutLight));
        mTabHost.addTab(mTabHost.newTabSpec("tab_5").setIndicator("Proximity").setContent(R.id.LinearLayoutProx));
        mTabHost.addTab(mTabHost.newTabSpec("tab_6").setIndicator("Pressure").setContent(R.id.LinearLayoutPres));
        mTabHost.addTab(mTabHost.newTabSpec("tab_7").setIndicator("Temperatur").setContent(R.id.LinearLayoutTemp));
        mTabHost.addTab(mTabHost.newTabSpec("tab_8").setIndicator("Overview").setContent(R.id.ScrollViewOverview));
        
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
        
        //connectSensors();
    }
    
    SensorEventListener senseventListener = new SensorEventListener(){

		
		@Override
		public void onSensorChanged(SensorEvent event) {
			String accuracy;
			
			switch(event.accuracy){
				case SensorManager.SENSOR_STATUS_ACCURACY_HIGH: accuracy="SENSOR_STATUS_ACCURACY_HIGH";break;
				case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM: accuracy="SENSOR_STATUS_ACCURACY_MEDIUM";break;
				case SensorManager.SENSOR_STATUS_ACCURACY_LOW: accuracy="SENSOR_STATUS_ACCURACY_LOW";break;
				case SensorManager.SENSOR_STATUS_UNRELIABLE: accuracy="SENSOR_STATUS_UNRELIABLE";break;
				default: accuracy="UNKNOWN";
			}
			
			if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
				   oriAccu.setText(accuracy);
			       pb_orientationA.setProgress( (int)event.values[0]);
			       pb_orientationB.setProgress( Math.abs((int)event.values[1]));
			       pb_orientationC.setProgress( Math.abs((int)event.values[2]));
			       tv_orientationA.setText(""+event.values[0]);
			       tv_orientationB.setText(""+event.values[1]);
			       tv_orientationC.setText(""+event.values[2]);
			}
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				   accAccu.setText(accuracy);
				   pb_accelA.setProgress( Math.abs( (int) event.values[0]*FLOATTOINTPRECISION ));
				   pb_accelB.setProgress( Math.abs( (int) event.values[1]*FLOATTOINTPRECISION ));
				   pb_accelC.setProgress( Math.abs( (int) event.values[2]*FLOATTOINTPRECISION ));
				   tv_accelA.setText(String.format("%.2f",event.values[0]));
				   tv_accelB.setText(String.format("%.2f",event.values[1]));
				   tv_accelC.setText(String.format("%.2f",event.values[2]));
				}
			if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
				   magAccu.setText(accuracy);
				   pb_magneticA.setProgress( Math.abs((int)event.values[0]*FLOATTOINTPRECISION ));
				   pb_magneticB.setProgress( Math.abs((int)event.values[1]*FLOATTOINTPRECISION ));
				   pb_magneticC.setProgress( Math.abs((int)event.values[2]*FLOATTOINTPRECISION ));
				   tv_magneticA.setText(String.format("%.2f",event.values[0]));
				   tv_magneticB.setText(String.format("%.2f",event.values[1]));
				   tv_magneticC.setText(String.format("%.2f",event.values[2]));
				}
			if(event.sensor.getType()==Sensor.TYPE_LIGHT){
				   ligAccu.setText(accuracy);
				   pb_lightA.setProgress( Math.abs((int)event.values[0]));
				   tv_lightA.setText(String.format("%.2f",event.values[0]));
				}
			if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
				   proxAccu.setText(accuracy);
				   pb_proxA.setProgress( Math.abs((int)event.values[0]));
				   tv_proxA.setText(String.format("%.2f",event.values[0]));
				}
			if(event.sensor.getType()==Sensor.TYPE_PRESSURE){
				   presAccu.setText(accuracy);
				   pb_presA.setProgress( Math.abs((int)event.values[0]));
				   tv_presA.setText(String.format("%.2f",event.values[0]));
				}
			if(event.sensor.getType()==Sensor.TYPE_TEMPERATURE){
				   tempAccu.setText(accuracy);
				   pb_tempA.setProgress( Math.abs((int)event.values[0]));
				   tv_tempA.setText(String.format("%.2f",event.values[0]));
				}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
			
		}
    	
    };


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.quit: finish();break;
			default: break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}


	@Override
	protected void onPause() {
		m_sensormgr.unregisterListener(senseventListener);
		super.onPause();
	}


	@Override
	protected void onResume() {
		connectSensors();
		super.onResume();
	}
	
	protected String getSensorInfo(Sensor sen){
	 String sensorInfo="INVALID";
	 String snsType;
	 
	 switch(sen.getType()){
	 	case Sensor.TYPE_ACCELEROMETER     : snsType="TYPE_ACCELEROMETER";break;
	 	case Sensor.TYPE_ALL               : snsType="TYPE_ALL";break;
	 	case Sensor.TYPE_GYROSCOPE         : snsType="TYPE_GYROSCOPE";break;
	 	case Sensor.TYPE_LIGHT             : snsType="TYPE_LIGHT";break;
	 	case Sensor.TYPE_MAGNETIC_FIELD    : snsType="TYPE_MAGNETIC_FIELD";break;
	 	case Sensor.TYPE_ORIENTATION       : snsType="TYPE_ORIENTATION";break;
	 	case Sensor.TYPE_PRESSURE          : snsType="TYPE_PRESSURE";break;
	 	case Sensor.TYPE_PROXIMITY         : snsType="TYPE_PROXIMITY";break;
	 	case Sensor.TYPE_TEMPERATURE       : snsType="TYPE_TEMPERATURE";break;
	 	default: snsType="UNKNOWN_TYPE "+sen.getType();break;
	 }

	 sensorInfo=sen.getName()+"\n";
	 sensorInfo+="Version: "+sen.getVersion()+"\n";
	 sensorInfo+="Vendor: "+sen.getVendor()+"\n";
	 sensorInfo+="Type: "+snsType+"\n";
	 sensorInfo+="MaxRange: "+sen.getMaximumRange()+"\n";
	 sensorInfo+="Resolution: "+String.format("%.5f",sen.getResolution())+"\n";
	 sensorInfo+="Power: "+sen.getPower()+" mA\n";
	 return sensorInfo;	
	}
	
	
	protected void connectSensors(){
		m_sensormgr.unregisterListener(senseventListener);
		if(!m_sensorlist.isEmpty()){
        	Sensor snsr;
        	for(int i=0;i<m_sensorlist.size();i++){
        		snsr=m_sensorlist.get(i);
        		
        		if(snsr.getType()==Sensor.TYPE_ORIENTATION && mTabHost.getCurrentTab()== 0 ){
        			oriHead.setText(getSensorInfo(snsr));
        			pb_orientationA.setMax((int)snsr.getMaximumRange());
        			pb_orientationB.setMax((int)snsr.getMaximumRange());
        			pb_orientationC.setMax((int)snsr.getMaximumRange());
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_ACCELEROMETER && mTabHost.getCurrentTab()== 1){
        			accHead.setText(getSensorInfo(snsr));
        			pb_accelA.setMax((int)(snsr.getMaximumRange()*SensorManager.GRAVITY_EARTH*FLOATTOINTPRECISION));
        			pb_accelB.setMax((int)(snsr.getMaximumRange()*SensorManager.GRAVITY_EARTH*FLOATTOINTPRECISION));
        			pb_accelC.setMax((int)(snsr.getMaximumRange()*SensorManager.GRAVITY_EARTH*FLOATTOINTPRECISION));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_MAGNETIC_FIELD && mTabHost.getCurrentTab()== 2){
        			magHead.setText(getSensorInfo(snsr));
        			pb_magneticA.setMax((int)(snsr.getMaximumRange()*FLOATTOINTPRECISION));
        			pb_magneticB.setMax((int)(snsr.getMaximumRange()*FLOATTOINTPRECISION));
        			pb_magneticC.setMax((int)(snsr.getMaximumRange()*FLOATTOINTPRECISION));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_LIGHT && mTabHost.getCurrentTab()== 3){
        			ligHead.setText(getSensorInfo(snsr));
        			pb_lightA.setMax((int)(snsr.getMaximumRange()));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_PROXIMITY && mTabHost.getCurrentTab()== 4){
        			proxHead.setText(getSensorInfo(snsr));
        			pb_proxA.setMax((int)(snsr.getMaximumRange()));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_PRESSURE && mTabHost.getCurrentTab()== 5){
        			presHead.setText(getSensorInfo(snsr));
        			pb_presA.setMax((int)(snsr.getMaximumRange()));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(snsr.getType()==Sensor.TYPE_TEMPERATURE && mTabHost.getCurrentTab()== 6){
        			tempHead.setText(getSensorInfo(snsr));
        			pb_tempA.setMax((int)(snsr.getMaximumRange()));
        			m_sensormgr.registerListener(senseventListener, snsr, SensorManager.SENSOR_DELAY_NORMAL);
        		}
        		if(mTabHost.getCurrentTab()== 7){
        			tv_overview.append(getSensorInfo(snsr)+"\n\n");
        		}
        	}
        }
	}


	@Override
	public void onTabChanged(String arg0) {
		tv_overview.setText("");
		connectSensors();
	}
}