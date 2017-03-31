package frame;
//===========================================
//需要自动大小的容器保存类 ObjectResizeBox.java
//===========================================
public class ObjectResizeBox 
{
	String type;
	Object obj;
	ObjectResizeBox(String type,Object obj)
	{
		this.type = type;
		this.obj = obj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
