# frozen_string_literal: true

require 'minitest/autorun'
require 'stringio'
require_relative '../../tool/marshal_inspect'

class TestMarshalInspect < Minitest::Test
  def inspect_dump(dump)
    output = StringIO.new
    parser = MarshalInspect.inspect(dump, output: output)
    [parser, output.string]
  end

  def test_renders_nested_values_and_resolves_links
    value = 'shared'
    parser, output = inspect_dump(Marshal.dump([value, value, :name, :name]))

    assert_equal 2, parser.objects.length
    assert_equal 2, parser.symbols.length # :E for the String encoding and :name
    assert_includes output, 'root: "[" array count=4'
    assert_includes output, 'element[1]: "@" object-link index=1 -> string wrapped object'
    assert_includes output, 'element[3]: ";" symbol-link index=1 -> "name"'
    assert_includes output, 'Object links (2)'
    assert_includes output, 'Symbol links (2)'
  end

  def test_user_class_symbol_enters_symbol_table
    # [C :Hash {}, :after, ;1]. A reader must register :Hash even though it is
    # metadata for the C wrapper; otherwise the final symbol link drifts.
    dump = "\x04\x08[\x08C:\x09Hash{\x00:\x0aafter;\x06".b
    parser, output = inspect_dump(dump)

    assert_equal ['"Hash"', '"after"'], parser.symbols.map(&:description)
    assert_includes output, '"C" user-class'
    assert_includes output, 'symbol-link index=1 -> "after"'
  end

  def test_userdef_registers_after_wrapper_instance_variable_values
    # I u :X "x" 1 :a "v"
    dump = "\x04\x08Iu:\x06X\x06x\x06:\x06a\"\x06v".b
    parser, output = inspect_dump(dump)

    assert_equal %w[string user-defined], parser.objects.map { |entry| entry.description.split.first }
    assert_includes output, 'object registration deferred until wrapper ivars finish'
    assert_match(/string instance variable\[0\]\.value/, parser.objects[0].description)
    assert_match(/user-defined wrapped object/, parser.objects[1].description)
  end

  def test_reports_invalid_link_with_offset_and_path
    error = assert_raises(MarshalInspect::FormatError) do
      MarshalInspect::Parser.new("\x04\x08@\x06".b).parse
    end

    assert_includes error.message, 'invalid object link 1; table contains 0 entries'
    assert_includes error.message, 'offset 0x00000004 (4)'
    assert_includes error.message, 'root > root'
  end

  def test_reports_declared_count_that_runs_past_end
    # Array declares two elements but contains only nil.
    error = assert_raises(MarshalInspect::FormatError) do
      MarshalInspect::Parser.new("\x04\x08[\x070".b).parse
    end

    assert_includes error.message, 'unexpected end of input while reading type tag'
    assert_includes error.message, 'element[1]'
  end

  def test_reports_trailing_bytes
    error = assert_raises(MarshalInspect::FormatError) do
      MarshalInspect::Parser.new("\x04\x0800".b).parse
    end

    assert_includes error.message, '1 trailing byte(s) after root object'
    assert_includes error.message, 'offset 0x00000003 (3)'
  end

  def test_accepts_older_minor_version
    parser, output = inspect_dump("\x04\x070".b)

    assert_equal [4, 7], parser.version
    assert_includes output, 'Marshal 4.7 header='
  end
end
