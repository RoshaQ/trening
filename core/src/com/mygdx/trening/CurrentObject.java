package com.mygdx.trening;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

//import java.awt.Rectangle;

import sun.plugin.dom.css.Rect;

/**
 * Created by mikol on 14.04.2016.
 */
public class CurrentObject extends Rectangle
{

    private Texture texture;

    public CurrentObject(Texture texture)
    {
        this.texture = texture;
    }
    public Texture getTexture()
    {
        return texture;
    }


}
