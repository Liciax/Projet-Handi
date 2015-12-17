package fr.unantes.beans;


import javax.jdo.annotations.*;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Geof
 * @date 12-12-2015
 */
@Entity
public class Website {

	@Id
    String url;
    private List<Layout> layouts;
	

    public Website() {}
    
    public Website(String url){
    	this.url = url;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public boolean contains(Layout layout){
		for(Layout each : this.layouts){
			if(each.getId() == layout.getId()){
				return true;
			}
		}
		return false;
	}

}
