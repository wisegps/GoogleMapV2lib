package android;

import java.math.BigDecimal;
import java.util.List;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * @author Wu
 * @date  2016-05-27
 */
public class WGoogleMap {
	
	public GoogleMap googleMap;
	public CameraPosition cameraPosition;
	
	public static final int MAP_TYPE_NORMAL    = 1;//��ͨ�ֵ���ͼ
	public static final int MAP_TYPE_SATELLITE = 2;//������ͼ
	public static final int MAP_TYPE_TERRAIN   = 3;//���Ƶ�ͼ
	public static final int MAP_TYPE_HYBRID    = 4;//���Ǻ�·���Ļ����ͼ
	
	public static final int DIS_TYPE_KM = 1;//ǧ��
	public static final int DIS_TYPE_MI = 2;//Ӣ��
	public static final int DIS_TYPE_NMI= 3;//����
	
	public WGoogleMap(GoogleMap map){
		super();
		this.googleMap = map;
		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}
	
	/**
	 * @param enable �Ƿ���ʾ�ҵ�λ�ð�ť
	 */
	public void setMyLoaction(boolean enable){
		googleMap.setMyLocationEnabled(enable);
	}

	/**
	 * @param enable �Ƿ�����ͼ������ʾ���ű����ؼ�
	 */
	public void setZoomControlsEnabled(boolean enable){
		googleMap.getUiSettings().setZoomControlsEnabled(enable);
	}
	
	/**
	 * @param enable  ���û��鿴���ڵ�ͼʱ�򣬲㼶ѡ������������Ҳ��Ե
	 */
	public void setIndoorLevelPickerEnabled(boolean enable){
		googleMap.getUiSettings().setIndoorLevelPickerEnabled(enable);
	}
	
	/**
	 * @param enable  ��ͼ������������ת��Google����������ߵ�ͼ�����
	 */
	public void setMapToolbarEnabled(boolean enable){
		googleMap.getUiSettings().setMapToolbarEnabled(enable);
	}
	
	/**
	 * @param enable ��б���� (Ĭ�Ͽ���)
	 */
	public void setTiltGesturesEnabled(boolean enable){
		googleMap.getUiSettings().setTiltGesturesEnabled(enable);
	}
	
	/**
	 * @param enable ��ת���� (Ĭ�Ͽ���)
	 */
	public void setRotateGesturesEnabled(boolean enable){
		googleMap.getUiSettings().setRotateGesturesEnabled(enable);
	}
	
	/**
	 * @param enable �������� (Ĭ�Ͽ���)
	 */
	public void setZoomGesturesEnabled(boolean enable){
		googleMap.getUiSettings().setZoomGesturesEnabled(enable);
	}
	
	/**
	 * @param enable ƽ������ (Ĭ�Ͽ���)
	 */
	public void setScrollGesturesEnabled(boolean enable){
		googleMap.getUiSettings().setScrollGesturesEnabled(true);
	}
	
	/**
	 * @param enable ����Щ���зŴ����ʾ3D��ͼ
	 */
	public void setBuildingsEnabled(boolean enable){
		googleMap.setBuildingsEnabled(enable);
	}
	
	
	/**
	 * @param mapType 1:��ͨ�ֵ���ͼ 2:������ͼ 3:���Ƶ�ͼ 4:���Ǻͽֵ������ͼ��Ĭ����normal��
	 */
	public void setMapType(int mapType){
		switch (mapType) {
		case 1:
			googleMap.setMapType(MAP_TYPE_NORMAL);
			break;
		case 2:
			googleMap.setMapType(MAP_TYPE_SATELLITE);
			break;
		case 3:
			googleMap.setMapType(MAP_TYPE_TERRAIN);
			break;
		case 4:
			googleMap.setMapType(MAP_TYPE_HYBRID);
			break;
		default:
			googleMap.setMapType(MAP_TYPE_NORMAL);
			break;
		}
	}
	
	
	/**
	 * @param target ����Ҫ�ƶ���ͷ��Ŀ�꣨�ƶ��������ڵ�ͼ���ģ�
	 * @param zoom �������ż���
	 * @param bearing ��������ķ���
	 * @param tilt ��ͷ����б��
	 */
	public void animateCamera(LatLng target,int zoom,int bearing,int tilt){
		CameraPosition cameraPosition = new CameraPosition.Builder()
	    .target(target)      // Sets the center of the map to Mountain View
	    .zoom(13)            // Sets the zoom
	    .bearing(bearing)    // Sets the orientation of the camera to east
	    .tilt(tilt)          // Sets the tilt of the camera to 30 degrees
	    .build();            // Creates a CameraPosition from the builder
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}
	
	
	/**
	 * @param latlng ��ǩ��λ��
	 * @param markName ��ǩ��ʾ����Ϣ
	 * @return Marker
	 */
	public Marker addMarker(LatLng latlng,String markName){
		return googleMap.addMarker(new MarkerOptions().position(latlng).title(markName));
	}
	
	/**
	 * @param latlng �Ѿ�ͷ�ƶ����ĵ�
	 */
	public void moveCamera(LatLng latlng){
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
	}
	
	
	
	/**
	 * @param latlng
	 * @param resId
	 * @return MarkerOptions
	 */
	public MarkerOptions setMarkOptions(LatLng latlng,int resId){
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(resId);
		MarkerOptions markOption = new MarkerOptions().anchor(0.1f, 0.1f)
				.position(latlng).icon(bitmap);
		return markOption;
	}
	
	
	/**
	 * @param markOptions
	 * @return
	 */
	public Marker addMarker(MarkerOptions markOptions){
		return googleMap.addMarker(markOptions);
	}
	
	
	/**
	 * @param seconds λ�ø���ʱ�� (��λΪ��/s)
	 * @return LocationRequest
	 */
	public LocationRequest setLocationRequest(int seconds){
		LocationRequest REQUEST = LocationRequest.create()
	            .setInterval(1000 * seconds)         // 5 seconds
	            .setFastestInterval(16)    // 16ms = 60fps
	            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		return REQUEST;
	}
	
	/**
	 * @param points ��γ�ȵ㼯��
	 * @param color  ����ɫ
	 * @param width  �߿��
	 */
	public void drawLine(List<LatLng> points,int color,float width){
		PolylineOptions options = new PolylineOptions(); 
		options.color(color);
		options.width(width);
		options.geodesic(true);
		options.addAll(points);
		googleMap.addPolyline(options);
	}
	
	/**
	 * @param start ��ʼ��γ�ȵ�
	 * @param end   ������γ�ȵ�
	 * @param color ����ɫ 
	 * @param width �߿��
	 */
	public void drawLine(LatLng start, LatLng end,int color,float width){
		googleMap.addPolyline((new PolylineOptions())
	            .add(start,end)
	            .width(width)
	            .color(color)
	            .geodesic(true));
	}
	
	/**
	 * �����ͼ����Ķ���
	 */
	public void clear(){
		googleMap.clear();
	}
	
	
	/**
	 * @param marker �Ƴ�marker
	 */
	public void removeMarker(Marker marker){
		marker.remove();
	}
	
	
	/**
	 * @param client GoogleApiClient
	 * @param seconds  ��λʱ����
	 * @param listener ��λ����
	 */
	public void setMyLocationListener(GoogleApiClient client,int seconds,LocationListener listener){
		LocationServices.FusedLocationApi.requestLocationUpdates(
				client,
				setLocationRequest(seconds),
				listener);  // LocationListener  
	}
	
	
	/**
	 * ��ȡ����ľ���
	 * @param start  ��ʼ��γ��
	 * @param end    �����㾭γ��
	 * @param type   1��km 2: mi 3:nmi
	 * @return
	 */
	public double getDistance(LatLng start, LatLng end,int type){
		float[] results = new float[1];
		double dis = 0;
	    Location.distanceBetween(start.latitude, start.longitude, end.latitude, end.longitude, results); 
	    if(type == DIS_TYPE_KM){
	    	BigDecimal b = new BigDecimal(results[0]/1000);
		    dis = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }else if(type == DIS_TYPE_MI){
	    	BigDecimal b = new BigDecimal(results[0]/1000*0.6213712);
		    dis = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }else if(type == DIS_TYPE_NMI){
	    	BigDecimal b = new BigDecimal(results[0]/1000*0.5399568);
		    dis = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	    return dis;
	}
	

}
