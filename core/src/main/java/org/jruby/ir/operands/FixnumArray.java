package org.jruby.ir.operands;

import org.jruby.RubyArray;
import org.jruby.ir.IRVisitor;
import org.jruby.ir.persistence.IRReaderDecoder;
import org.jruby.ir.persistence.IRWriterEncoder;
import org.jruby.ir.transformations.inlining.CloneInfo;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

import java.util.Arrays;
import java.util.List;

import static org.jruby.api.Convert.asFixnum;
import static org.jruby.api.Create.newArray;
import static org.jruby.api.Create.newArrayNoCopy;

/**
 * An array of fixnums, stored in IR as bytes for compactness. When executed,
 * the bytes are converted into a RubyArray of fixnums.
 *
 * If the array is only requested once, the resulting array is the only
 * instance created. If it is requested more than once, a preconstructed
 * array is cached for future shared duplication.
 *
 * This saves the space of having as many Operands as there are bytes in the
 * array, at the cost of a cached duplicate for byte arrays constructed
 * repeatedly.
 */
public class FixnumArray extends Operand  {
    public final long[] longs;
    public final

    public FixnumArray(long[] elts, Fixnum[] fixnums) {
        super();

        this.longs = elts;
        this.fixnums = fixnums;
    }

    @Override
    public OperandType getOperandType() {
        return OperandType.LONGARRAY;
    }

    @Override
    public String toString() {
        return "ByteArray:" + (Arrays.toString(longs));
    }

    // ---------- These methods below are used during compile-time optimizations -------
    @Override
    public boolean hasKnownValue() {
        return true;
    }

    @Override
    public void addUsedVariables(List<Variable> l) {
    }

    public Operand toArray() {
        return this;
    }

    @Override
    public Operand cloneForInlining(CloneInfo ii) {
        return new FixnumArray(longs);
    }

    @Override
    public void encode(IRWriterEncoder e) {
        super.encode(e);
        e.encode(longs);
    }

    public static FixnumArray decode(IRReaderDecoder d) {
        return new FixnumArray(d.decodeLongArray());
    }

    boolean executed = false;
    private RubyArray cached;

    @Override
    public Object retrieve(ThreadContext context, IRubyObject self, StaticScope currScope, DynamicScope currDynScope, Object[] temp) {
        if (executed) {
            RubyArray cached = initArray(context);
            return cached.aryDup();
        } else {
            executed = true;
            return constructArray(context);
        }
    }

    private RubyArray initArray(ThreadContext context) {
        RubyArray cached = this.cached;
        if (cached == null) {
            RubyArray<?> ary = constructArray(context);
            this.cached = cached = ary;
        }
        return cached;
    }

    private RubyArray<?> constructArray(ThreadContext context) {
        IRubyObject[] objs = new IRubyObject[longs.length];
        for (int i = 0; i < longs.length; i++) {
            long l = longs[i];
            objs[i] = asFixnum(context, l);
        }
        RubyArray<?> ary = newArrayNoCopy(context, objs);
        return ary;
    }

    @Override
    public void visit(IRVisitor visitor) {
        visitor.ByteArray(this);
    }
}
