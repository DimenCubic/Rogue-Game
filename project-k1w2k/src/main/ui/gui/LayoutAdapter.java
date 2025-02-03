package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;

// The cover class for layout.
public abstract class LayoutAdapter implements LayoutManager2 {

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    // @Override
    // public void removeLayoutComponent(Component comp) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'removeLayoutComponent'");
    // }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    // @Override
    // public void layoutContainer(Container parent) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'layoutContainer'");
    // }

    // @Override
    // public void addLayoutComponent(Component comp, Object constraints) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'addLayoutComponent'");
    // }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;

    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

}
