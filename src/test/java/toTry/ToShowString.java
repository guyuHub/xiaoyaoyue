package toTry;

import cn.com.doit.z.test.pojo.TestStudent;

/*javax.validation.constraints.AssertFalse.message = \u53EA\u80FD\u4E3Afalse 只能为
javax.validation.constraints.AssertTrue.message  = \u53EA\u80FD\u4E3Atrue
javax.validation.constraints.DecimalMax.message  = \u5FC5\u987B\u5C0F\u4E8E\u6216\u7B49\u4E8E{value} 必须小于或等于
javax.validation.constraints.DecimalMin.message  = \u5FC5\u987B\u5927\u4E8E\u6216\u7B49\u4E8E{value}
javax.validation.constraints.Digits.message      = \u6570\u5B57\u7684\u503C\u8D85\u51FA\u4E86\u5141\u8BB8\u8303\u56F4(\u53EA\u5141\u8BB8\u5728{integer}\u4F4D\u6574\u6570\u548C{fraction}\u4F4D\u5C0F\u6570\u8303\u56F4\u5185)  数字的值超出了允许范围(只允许在{integer}位整数和{fraction}位小数范围内)
javax.validation.constraints.Future.message      = \u9700\u8981\u662F\u4E00\u4E2A\u5C06\u6765\u7684\u65F6\u95F4 需要是一个将来的时间
javax.validation.constraints.Max.message         = \u6700\u5927\u4E0D\u80FD\u8D85\u8FC7{value}  最大不能超过{value}
javax.validation.constraints.Min.message         = \u6700\u5C0F\u4E0D\u80FD\u5C0F\u4E8E{value}
javax.validation.constraints.NotNull.message     = \u4E0D\u80FD\u4E3Anull  不能为null
javax.validation.constraints.Null.message        = \u5FC5\u987B\u4E3Anull  必须为null
javax.validation.constraints.Past.message        = \u9700\u8981\u662f\u4e00\u4e2a\u8fc7\u53bb\u7684\u65F6\u95F4 需要是一个过去的时间
javax.validation.constraints.Pattern.message     = \u9700\u8981\u5339\u914D\u6B63\u5219\u8868\u8FBE\u5F0F"{regexp}" 需要匹配正则表达式"{regexp}"

javax.validation.constraints.Size.message        = \u4E2A\u6570\u5FC5\u987B\u5728{min}\u548C{max}\u4E4B\u95F4  个数必须在{min}和{max}之间

org.hibernate.validator.constraints.CreditCardNumber.message = \u4E0D\u5408\u6CD5\u7684\u4FE1\u7528\u5361\u53F7\u7801 不合法的信用卡号码
org.hibernate.validator.constraints.Email.message            = \u4E0D\u662F\u4E00\u4E2A\u5408\u6CD5\u7684\u7535\u5B50\u90AE\u4EF6\u5730\u5740 不是一个合法的电子邮件地址
org.hibernate.validator.constraints.Length.message           = \u957F\u5EA6\u9700\u8981\u5728{min}\u548C{max}\u4E4B\u95F4 长度需要在{min}和{max}之间
org.hibernate.validator.constraints.NotBlank.message         = \u4E0D\u80FD\u4E3A\u7A7A 不能为空
org.hibernate.validator.constraints.NotEmpty.message         = \u4E0D\u80FD\u4E3A\u7A7A
org.hibernate.validator.constraints.Range.message            = \u9700\u8981\u5728{min}\u548C{max}\u4E4B\u95F4  需要在{min}和{max}之间
org.hibernate.validator.constraints.SafeHtml.message         = \u53EF\u80FD\u6709\u4E0D\u5B89\u5168\u7684HTML\u5185\u5BB9 可能有不安全的HTML内容
org.hibernate.validator.constraints.ScriptAssert.message     = \u6267\u884C\u811A\u672C\u8868\u8FBE\u5F0F"{script}"\u6CA1\u6709\u80FD\u591F\u5F97\u5230true 执行脚本表达式"{script}"没有能够得到true
org.hibernate.validator.constraints.URL.message              = \u9700\u8981\u662F\u4E00\u4E2A\u5408\u6CD5\u7684URL 需要是一个合法的URL
*/
public class ToShowString {
public static void main(String[] args) {
	System.out.println("%E5%93%87%E9%AD%94&");
	new TestStudent(){
		public String toSay(){
			System.out.println("==========================="); 
			System.err.println("\u672a\u5b9a\u4e49\u4efb\u4f55\u6570\u636e\u88c5\u8f7d\u5668\uff0c\u8bf7\u786e\u8ba4");//未定义任何数据装载器，请确认
			System.err.println("\u672a\u6307\u5b9aAuditObject\u5bf9\u8c61\uff0c\u76f4\u63a5\u8fd4\u56de\u3002");
			return null;
		}
	}.toSay();
}
}
