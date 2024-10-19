export default function PatientRootLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<div id='nutri-root-layout'>
            {children}
		</div>
	);
}
