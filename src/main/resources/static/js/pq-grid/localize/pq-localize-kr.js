/**
 * @author pdhindsa
 */
if("function"==typeof require)
    var jQuery = jQuery || require("jquery");

(function( $ ){

var pq = $.paramquery,
    local='kr',
    grid = pq.pqGrid.regional[local] = {
        strLocal: local,
        strAdd:  "추가",
        strBlanks:  "(공백)",
        strDelete:  "삭제",
        strEdit:  "편집",
        strCondition:  "조건 :",
        strConditions:  {
            "":  "-없음-",
            begin:  "시작",
            between:  "사이",
            contain:  "포함",
            equal:  "같음",
            empty:  "빈",
            end:  "끝",
            great:  "보다 큼",
            gte:  "보다 크거나 같음",
            less:  "보다 작음",
            lte:  "보다 작거나 같음",
            notbegin:  "시작하지 않음",
            notcontain:  "포함하지 않음",
            notequal:  "같지 않음",
            notempty:  "비어 있지 않음",
            notend:  "로 끝나지 않음",
            range:  "[범위]",
            regexp:  "정규 표현식"
        },
        strOk:  "적용",
        strClear:  "초기화",
        strTabRemove: "{0} 영구적으로 삭제됩니다. \r\n 진행 하시겠습니까??",
        strTabName: "sheet{0}",
        strTabAdd: "탭 추가",
        strTabClose: "탭 닫기",
        strGroup_header:  "열을 여기로 끌어 해당 열을 기준으로 그룹화하십시오",
        strGroup_merge:  "셀 병합",
        strGroup_fixCols:  "열 수정",
        strGroup_grandSummary:  "전체 요약",
        strTP_aggPane:  "집계",
        strTP_colPane:  "그룹 열",
        strTP_pivot:  "피벗 모드",
        strTP_rowPane:  "그룹 행",
        strTP_aggPH:  "집계 값 계산을위한 열 삭제",
        strTP_colPH:  "열 또는 x 축을 따라 그룹화하기 위해 여기에 열을 삭제하십시오",
        strTP_rowPH:  "행 또는 y 축을 따라 그룹화하기 위해 여기에 열을 삭제하십시오",
        strLoading:  "로드 중",
        strNextResult:  "다음 결과",
        strNoRows:  "표시 할 행이 없습니다.",
        strNothingFound:  "아무것도 없습니다",
        strPrevResult:  "이전 결과",
        strSearch:  "검색",
        strSelectAll:  "모두 선택",
        strSelectedmatches:  "{1} 일치 중 {0}을 (를) 선택했습니다.",
        strFormulas:{
            ABS: ["ABS(number)","Returns the absolute value of a number. The absolute value of a number is the number without its sign."],
            ACOS: ["ACOS(number)","Returns the arccosine, or inverse cosine, of a number. The arccosine is the angle whose cosine is number. The returned angle is given in radians in the range 0 (zero) to pi."],
            AND: ["AND(logical1, [logical2], ...)","Returns TRUE if all its arguments evaluate to TRUE, and returns FALSE if one or more arguments evaluate to FALSE."],
            ASIN: ["ASIN(number)","Returns the arcsine, or inverse sine, of a number. The arcsine is the angle whose sine is number. The returned angle is given in radians in the range -pi/2 to pi/2."],
            ATAN: ["ATAN(number)","Returns the arctangent, or inverse tangent, of a number. The arctangent is the angle whose tangent is number. The returned angle is given in radians in the range -pi/2 to pi/2."],
            AVERAGE: ["AVERAGE(number1, [number2], ...)","Returns the average (arithmetic mean) of the arguments. For example, if the range A1:A20 contains numbers, the formula =AVERAGE(A1:A20) returns the average of those numbers."],
            AVERAGEIF: ["AVERAGEIF(range, criteria, [average_range])","Returns the average (arithmetic mean) of all the cells in a range that meet a given criteria."],
            AVERAGEIFS: ["AVERAGEIFS(average_range, criteria_range1, criteria1, [criteria_range2, criteria2], ...)","Returns the average (arithmetic mean) of all cells that meet multiple criteria."],
            CEILING: ["CEILING(number, significance)","Returns number rounded up, away from zero, to the nearest multiple of significance. For example, if you want to avoid using pennies in your prices and your product is priced at $4.42, use the formula =CEILING(4.42,0.05) to round prices up to the nearest nickel."],
            CHAR: ["CHAR(number)","Returns the character specified by a number."],
            CHOOSE: ["CHOOSE(index_num, value1, [value2], ...)","If index_num is 1, CHOOSE returns value1; if it is 2, CHOOSE returns value2; and so on."],
            CODE: ["CODE(text)","Returns a numeric code for the first character in a text string."],
            COLUMN: ["COLUMN()","Returns reference of the cell in which the COLUMN function appears."],
            COLUMNS: ["COLUMNS(array)","Returns the number of columns in an array or reference."],
            CONCATENATE: ["CONCATENATE(text1, [text2], ...)","join two or more text strings into one string."],
            COS: ["COS(number)","Returns the cosine of the given angle(in radians)."],
            COUNT: ["COUNT(value1, [value2], ...)","Counts the number of cells that contain numbers, and counts numbers within the list of arguments."],
            COUNTA: ["COUNTA(value1, [value2], ...)","The COUNTA function counts the number of cells that are not empty in a range."],
            COUNTBLANK: ["COUNTBLANK(range)","Counts empty cells in a specified range of cells."],
            COUNTIF: ["COUNTIF(range, criteria)","counts the number of cells that meet a criterion; for example, to count the number of times a particular city appears in a customer list."],
            COUNTIFS: ["COUNTIFS(criteria_range1, criteria1, [criteria_range2, criteria2]…)","Applies criteria to cells across multiple ranges and counts the number of times all criteria are met."],
            DATE: ["DATE(year,month,day)","returns the sequential serial number that represents a particular date."],
            DATEDIF: ["DATEDIF(start_date,end_date,unit)","Calculates the number of days, months, or years between two dates. Unit can be 'Y', 'M' or 'D'."],
            DATEVALUE: ["DATEVALUE(date_text)", "converts a date that is stored as text to a serial number that Excel recognizes as a date. For example, the formula =DATEVALUE(\"1/1/2008\") returns 39448."],
            DAY: ["DAY(serial_number)","Returns the day of a date, represented by a serial number."],
            DAYS: ["DAYS(end_date, start_date)","Returns the number of days between two dates."],
            DEGREES: ["DEGREES(angle)","Converts radians into degrees."],
            EOMONTH: ["EOMONTH(start_date, months)","Returns the serial number for the last day of the month that is the indicated number of months before or after start_date."],
            EXP: ["EXP(number)","Returns e raised to the power of number. "],
            FIND: ["FIND(find_text, within_text, [start_num])","Locates one text string within a second text string, and return the number of the starting position of the first text string from the first character of the second text string."],
            FLOOR: ["FLOOR(number, significance)","Rounds number down, toward zero, to the nearest multiple of significance."],
            HLOOKUP: ["HLOOKUP(lookup_value, table_array, row_index_num, [range_lookup])","Searches for a value in the top row of a table or an array of values, and then returns a value in the same column from a row you specify in the table or array."],
            HOUR: ["HOUR(serial_number)","Returns the hour of a time value. The hour is given as an integer, ranging from 0 (12:00 A.M.) to 23 (11:00 P.M.)."],
            IF: ["IF(logical_test, value_if_true, [value_if_false])","returns one value if a condition is true and another value if it's false."],
            //IFERROR: ["IFERROR(value, value_if_error)","Returns a value you specify if a formula evaluates to an error; otherwise, returns the result of the formula. Use the IFERROR function to trap and handle errors in a formula."],
            INDEX: ["INDEX(array, row_num, [column_num])","Returns the value of an element in a table or an array, selected by the row and column number indexes."],
            INDIRECT: ["INDIRECT(ref_text, [a1])","Returns the reference specified by a text string. References are immediately evaluated to display their contents."],
            ISBLANK: ["ISBLANK(value)","Returns TRUE if the value is blank"],
            LARGE: ["LARGE(array, k)","Returns the k-th largest value in a data set."],
            LEFT: ["LEFT(text, [num_chars])","returns the first character or characters in a text string, based on the number of characters you specify."],
            LEN: ["LEN(text)","returns the number of characters in a text string."],
            LOOKUP:["LOOKUP(lookup_value, lookup_vector, [result_vector])","Looks in a one-row or one-column range (known as a vector) for a value and returns a value from the same position in a second one-row or one-column range."],
            LOWER: ["LOWER(text)","Converts all uppercase letters in a text string to lowercase."],
            MATCH: ["MATCH(lookup_value, lookup_array, [match_type])","Searches for a specified item in a range of cells, and then returns the relative position of that item in the range. match_type can be: 0 for exact match with option to use wildcards; 1 ( default ) for less than, The values in the lookup_array argument must be placed in ascending order; -1 for greater than, values in the lookup_array argument must be placed in descending order."],
            MAX: ["MAX(number1, [number2], ...)","Returns the largest value in a set of values."],
            MEDIAN: ["MEDIAN(number1, [number2], ...)","Returns the median of the given numbers. The median is the number in the middle of a set of numbers."],
            MID: ["MID(text, start_num, num_chars)","returns a specific number of characters from a text string, starting at the position you specify, based on the number of characters you specify."],
            MIN: ["MIN(number1, [number2], ...)","Returns the smallest number in a set of values."],
            MODE: ["MODE(number1,[number2],...)","Returns the most frequently occurring, or repetitive, value in an array or range of data."],
            MONTH: ["MONTH(serial_number)","Returns the month of a date represented by a serial number. The month is given as an integer, ranging from 1 (January) to 12 (December)."],
            OR: ["OR(logical1, [logical2], ...)","returns TRUE if any of its arguments evaluate to TRUE, and returns FALSE if all of its arguments evaluate to FALSE."],
            PI: ["PI()","Returns the number 3.14159265358979, the mathematical constant pi."],
            POWER: ["POWER(number, power)","Returns the result of a number raised to a power."],
            PRODUCT: ["PRODUCT(number1, [number2], ...)","multiplies all the numbers given as arguments and returns the product. PRODUCT(A1:A3, C1:C3) is equivalent to =A1 * A2 * A3 * C1 * C2 * C3."],
            PROPER: ["PROPER(text)","Capitalizes the first letter in each word of a text value."],
            RADIANS: ["RADIANS(angle)","Converts degrees into radians."],
            RAND: ["RAND()","Returns an evenly distributed random real number greater than or equal to 0 and less than 1."],
            RANK: ["RANK(number,ref,[order])","Returns the rank of a number in a list of numbers."],
            RATE: ["",""],
            REPLACE:["REPLACE(old_text, start_num, num_chars, new_text)","replaces part of a text string, based on the number of characters you specify, with a different text string."],
            REPT:["REPT(text, number_times)","Repeats text a given number of times."],
            RIGHT: ["RIGHT(text,[num_chars])","returns the last character or characters in a text string, based on the number of characters you specify."],
            ROUND: ["ROUND(number, num_digits)","rounds a number to a specified number of digits."],
            ROUNDDOWN: ["ROUNDDOWN(number, num_digits)","Rounds a number down, toward zero."],
            ROUNDUP: ["ROUNDUP(number, num_digits)","Rounds a number up, away from 0 (zero)."],
            ROW: ["ROW()","Returns the reference of the cell in which the ROW function appears."],
            ROWS: ["ROWS(array)","Returns the number of rows in a reference or array."],
            SEARCH: ["SEARCH(find_text,within_text,[start_num])","locate one text string within a second text string, and return the number of the starting position of the first text string from the first character of the second text string."],
            SIN: ["SIN(number)","Returns the sine of the given angle(in radians)."],
            SMALL: ["SMALL(array, k)","Returns the k-th smallest value in a data set."],
            SQRT: ["SQRT(number)","Returns a positive square root."],
            STDEV: ["STDEV(number1,[number2],...)","Estimates standard deviation based on a sample. The standard deviation is a measure of how widely values are dispersed from the average value (the mean)."],
            STDEVP: ["STDEVP(number1,[number2],...)","Calculates standard deviation based on the entire population given as arguments."],
            SUBSTITUTE:["SUBSTITUTE(text, old_text, new_text, [instance_num])","Substitutes new_text for old_text in a text string."],
            SUM: ["SUM(number1,[number2],...)","Adds its arguments. You can add individual values, cell references or ranges or a mix of all three."],        //different from actual specs,
            SUMIF: ["SUMIF(range, criteria, [sum_range])","adds the values in a range that meet criteria that you specify"],
            SUMIFS: ["SUMIFS(sum_range, criteria_range1, criteria1, [criteria_range2, criteria2], ...)","adds all of its arguments that meet multiple criteria."],
            SUMPRODUCT: ["SUMPRODUCT(array1, [array2], [array3], ...)","Multiplies corresponding components in the given arrays, and returns the sum of those products."],
            TAN:["TAN(number)","Returns the tangent of the given angle(in radians)."],
            TEXT:["TEXT(Value you want to format, \"Format code you want to apply\")","The TEXT function lets you change the way a number appears by applying formatting to it with format codes."],
            TIME: ["TIME(hour, minute, second)","Returns the decimal number for a particular time."],
            TIMEVALUE: ["TIMEVALUE(time_text)", "Returns the decimal number of the time represented by a text string. The decimal number is a value ranging from 0 (zero) to 0.99988426, representing the times from 0:00:00 (12:00:00 AM) to 23:59:59 (11:59:59 P.M.)."],
            TODAY: ["TODAY()","Returns the serial number of the current date."],
            TRIM: ["TRIM(text)","Removes all spaces from text except for single spaces between words."],
            TRUNC: ["TRUNC(number, [num_digits])","Truncates a number to an integer by removing the fractional part of the number."],
            UPPER: ["UPPER(text)","Converts text to uppercase."],
            VALUE: ["VALUE(text)","Converts a text string that represents a number to a number."],
            VAR: ["VAR(number1,[number2],...)","Estimates variance based on a sample."],
            VARP: ["VARP(number1,[number2],...)","Calculates variance based on the entire population."],
            VLOOKUP: ["VLOOKUP (lookup_value, table_array, col_index_num, [range_lookup])","look up a value in a table or a range by row. For example, look up a price of an automotive part by the part number."],
            YEAR: ["YEAR(serial_number)","Returns the year corresponding to a date. The year is returned as an integer in the range 1900-9999."]
        }
    },
    pager = pq.pqPager.regional[local] = {
        strDisplay:  "{2} 요소 중 {0} ~ {1} 표시",
        strFirstPage:  "첫 페이지",
        strLastPage:  "마지막 페이지",
        strNextPage:  "다음 페이지",
        strPage:  "{1}의 {0} 페이지",
        strPrevPage:  "이전 페이지",
        strRefresh:  "새로 고침",
        strRpp:  "페이지 당 레코드 수 : {0}"
    };

    $.extend( pq.pqGrid.defaults, grid );
    $.extend( pq.pqPager.defaults, pager );

})(jQuery)