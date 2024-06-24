package org.jruby.api;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyBignum;
import org.jruby.RubyClass;
import org.jruby.RubyFile;
import org.jruby.RubyFixnum;
import org.jruby.RubyHash;
import org.jruby.RubyInteger;
import org.jruby.RubyModule;
import org.jruby.RubyNumeric;
import org.jruby.RubyProc;
import org.jruby.RubyRange;
import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.runtime.JavaSites;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

import static org.jruby.RubyBignum.big2long;
import static org.jruby.api.Error.typeError;
import static org.jruby.util.TypeConverter.convertToTypeWithCheck;
import static org.jruby.util.TypeConverter.sites;

public class Convert {
    /**
     * Cast the given value to a RubyArray with most basic typeError thrown
     * if the value is not a RubyArray.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyArray
     */
    public static RubyArray castToArray(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyArray)) throw typeError(context, newValue, "Array");
        return (RubyArray) newValue;
    }

    /**
     * Cast the given value to a RubyArray with most basic typeError thrown
     * if the value is not a RubyArray. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyArray
     */
    public static RubyArray castToArray(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyArray)) throw typeError(context, newValue, message);
        return (RubyArray) newValue;
    }

    /**
     * Cast the given value to a RubyBignum with most basic typeError thrown
     * if the value is not a RubyBignum.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyBignum
     */
    public static RubyBignum castToBignum(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyBignum)) throw typeError(context, newValue, "Bignum");
        return (RubyBignum) newValue;
    }

    /**
     * Cast the given value to a RubyClass with most basic typeError thrown
     * if the value is not a RubyClass.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyClass
     */
    public static RubyClass castToClass(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyClass)) throw typeError(context, newValue, "Class");
        return (RubyClass) newValue;
    }

    /**
     * Cast the given value to a RubyFile with most basic typeError thrown
     * if the value is not a RubyFile.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyFile
     */
    public static RubyFile castToFile(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyFile)) throw typeError(context, newValue, "File");
        return (RubyFile) newValue;
    }

    /**
     * Cast the given value to a RubyFixnum with most basic typeError thrown
     * if the value is not a RubyFixnum.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyFixnum
     */
    public static RubyFixnum castToFixnum(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyFixnum)) throw typeError(context, newValue, "Fixnum");
        return (RubyFixnum) newValue;
    }

    /**
     * Cast the given value to a RubyFixnum with most basic typeError thrown
     * if the value is not a RubyFixnum. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyFixnum
     */
    public static RubyFixnum castToFixnum(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyFixnum)) throw typeError(context, message);
        return (RubyFixnum) newValue;
    }

    /**
     * Cast the given value to a RubyHash with most basic typeError thrown
     * if the value is not a RubyHash.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyHash
     */
    public static RubyHash castToHash(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyHash)) throw typeError(context, newValue, "Hash");
        return (RubyHash) newValue;
    }

    /**
     * Cast the given value to a RubyHash with most basic typeError thrown
     * if the value is not a RubyHash. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyHash
     */
    public static RubyHash castToHash(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyHash)) throw typeError(context, message);
        return (RubyHash) newValue;
    }

    /**
     * Cast the given value to a RubyInteger with most basic typeError thrown
     * if the value is not a RubyInteger.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyInteger
     */
    public static RubyInteger castToInteger(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyInteger)) throw typeError(context, newValue, "Integer");
        return (RubyInteger) newValue;
    }

    /**
     * Cast the given value to a RubyInteger with most basic typeError thrown
     * if the value is not a RubyInteger. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyInteger
     */
    public static RubyInteger castToInteger(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyInteger)) throw typeError(context, message);
        return (RubyInteger) newValue;
    }


    /**
     * Cast the given value to a RubyModule with most basic typeError thrown
     * if the value is not a RubyModule.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyModule
     */
    public static RubyModule castToModule(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyModule)) throw typeError(context, newValue, "Module");
        return (RubyModule) newValue;
    }

    /**
     * Cast the given value to a RubyModule with most basic typeError thrown
     * if the value is not a RubyModule.  Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyModule
     */
    public static RubyModule castToModule(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyModule)) throw typeError(context, message);
        return (RubyModule) newValue;
    }

    /**
     * Cast the given value to a RubyNumeric with most basic typeError thrown
     * if the value is not a RubyNumeric. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyNumeric
     */
    public static RubyNumeric castToNumeric(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyNumeric)) throw typeError(context, message);
        return (RubyNumeric) newValue;
    }

    /**
     * Cast the given value to a RubyProc with most basic typeError thrown
     * if the value is not a RubyProc.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyProc
     */
    public static RubyProc castToProc(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyProc)) throw typeError(context, newValue, "Proc");
        return (RubyProc) newValue;
    }

    /**
     * Cast the given value to a RubyProc with most basic typeError thrown
     * if the value is not a RubyProc. Note: if message is constructed you will pay
     * that contruction cost.  Manually cast to avoid that overhead in that case.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @param message the message to include in the type error
     * @return the value as a RubyProc
     */
    public static RubyProc castToProc(ThreadContext context, IRubyObject newValue, String message) {
        if (!(newValue instanceof RubyProc)) throw typeError(context, message);
        return (RubyProc) newValue;
    }

    /**
     * Cast the given value to a RubyRange with most basic typeError thrown
     * if the value is not a RubyRange.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyRange
     */
    public static RubyRange castToRange(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyRange)) throw typeError(context, newValue, "Range");
        return (RubyRange) newValue;
    }

    /**
     * Cast the given value to a RubyString with most basic typeError thrown
     * if the value is not a RubyString.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubyString
     */
    public static RubyString castToString(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubyString)) throw typeError(context, newValue, "String");
        return (RubyString) newValue;
    }


    /**
     * Cast the given value to a RubySymbol with most basic typeError thrown
     * if the value is not a RubySymbol.
     *
     * @param context the current thread context
     * @param newValue the value to cast
     * @return the value as a RubySymbol
     */
    public static RubySymbol castToSymbol(ThreadContext context, IRubyObject newValue) {
        if (!(newValue instanceof RubySymbol)) throw typeError(context, newValue, "Symbol");
        return (RubySymbol) newValue;
    }

    // FIXME: Create annotation @MRI so we can formalize these comments and provide a dictionary for embedders.
    // MRI: rb_check_to_integer
    /**
     * Check whether the given object is an Integer or can be converted to an Integer using #to_int.
     * @param context the current thread context
     * @param obj the object to be converted
     * @return the integer value or nil if the object or conversion is not an Integer.
     */
    public static IRubyObject checkToInteger(ThreadContext context, IRubyObject obj) {
        if (obj instanceof RubyFixnum) return obj;

        JavaSites.TypeConverterSites sites = sites(context);

        IRubyObject conv = convertToTypeWithCheck(context, obj, context.runtime.getInteger(), sites.to_int_checked);

        return conv instanceof RubyInteger ? conv : context.nil;
    }

    /**
     * Safely convert a Ruby Integer into a java int value.  Raising if the value will not fit.
     * @param context the current thread context
     * @param value the RubyInteger to convert
     * @return the int value
     */
    public static int integerAsInt(ThreadContext context, RubyInteger value) {
        long num = value.getLongValue();

        if (((int) num) != num) {
            throw context.runtime.newRangeError("integer " + num +
                    (num < Integer.MIN_VALUE ? " too small to convert to `int'" : " too big to convert to `int'"));
        }

        return (int) num;
    }

    /**
     * Safely convert a Ruby Integer into a java int value.  Raising if the value will not fit.
     * @param context the current thread context
     * @param value the RubyInteger to convert
     * @return the int value
     */
    public static long integerAsLong(ThreadContext context, RubyInteger value) {
        if (value instanceof RubyBignum) {
            return big2long((RubyBignum) value);
        }

        return value.getLongValue();
    }
}