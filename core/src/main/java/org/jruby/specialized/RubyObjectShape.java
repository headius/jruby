package org.jruby.specialized;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.runtime.ivars.VariableTableManager;

public class RubyObjectShape extends RubyObject {
    private final VariableTableManager vtm;

    public RubyObjectShape(Ruby runtime, RubyClass metaClass) {
        super(runtime, metaClass);

        vtm = metaClass.getVariableTableManager();
    }
}
